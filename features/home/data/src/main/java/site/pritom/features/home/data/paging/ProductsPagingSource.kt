package site.pritom.features.home.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import retrofit2.HttpException
import site.pritom.features.home.data.datasource.ProductRemoteDataSource
import site.pritom.features.home.data.mapper.toDomainProduct
import site.pritom.features.home.domain.model.Product
import java.io.IOException

private const val STARTING_KEY = 0
private const val LOAD_DELAY_MILLIS = 3_000L

class ProductsPagingSource(
    private val api: ProductRemoteDataSource,
) : PagingSource<Int, Product>() {

    override suspend fun load(
        params: LoadParams<Int>,
    ): LoadResult<Int, Product> {

        return try {

            // `key` represents the `skip` value.
            // First request starts from 0.
            val skip = params.key ?: STARTING_KEY

            // Paging may request a larger initial load.
            val limit = params.loadSize

            val response = api.getProductList(
                limit = limit,
                skip = skip,
            )

            if (!response.isSuccessful) {
                return LoadResult.Error(
                    HttpException(response),
                )
            }

            val body = response.body()
                ?: return LoadResult.Error(
                    IllegalStateException("Empty response body"),
                )

            val products = body.products.map {
                it.toDomainProduct()
            }

            LoadResult.Page(
                data = products,
                // Previous page:
                // Example:
                // skip = 40 -> previous skip = 20
                prevKey = if (skip == STARTING_KEY) {
                    null
                } else {
                    maxOf(
                        STARTING_KEY,
                        skip - limit,
                    )
                },

                // Next page:
                // If we have reached the end, don't request another page.
                nextKey = if (
                    products.isEmpty() ||
                    skip + products.size >= body.total
                ) {
                    null
                } else {
                    skip + products.size
                },
            )

        } catch (e: IOException) {
            LoadResult.Error(e)

        } catch (e: HttpException) {
            LoadResult.Error(e)

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(
        state: PagingState<Int, Product>,
    ): Int? {

        val anchorPosition = state.anchorPosition
            ?: return null

        val anchorItem = state.closestItemToPosition(
            anchorPosition,
        ) ?: return null

        /*
         * Convert the currently visible item's position
         * into an approximate `skip` value.
         *
         * Example:
         *
         * pageSize = 20
         * anchor position = 45
         *
         * refresh key = 45 - (20 / 2)
         *              = 35
         *
         * This gives Paging a position around the
         * currently visible content after refresh.
         */
        return maxOf(
            STARTING_KEY,
            anchorPosition - (state.config.pageSize / 2),
        )
    }
}