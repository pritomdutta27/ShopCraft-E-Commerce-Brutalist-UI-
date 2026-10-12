package site.pritom.features.home.data.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import kotlinx.coroutines.delay
import retrofit2.HttpException
import site.prito.dutta.core.common.NetworkResult
import site.pritom.features.home.data.datasource.ProductRemoteDataSource
import site.pritom.features.home.data.mapper.toDomainProduct
import site.pritom.features.home.domain.model.Product
import site.pritom.network.utils.safeApiCall
import java.io.IOException
import kotlin.time.Duration.Companion.milliseconds

private const val STARTING_KEY = 0
private const val LOAD_DELAY_MILLIS = 3_000L

class ProductsPagingSource(
    private val api: ProductRemoteDataSource,
) : PagingSource<Int, Product>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Product> {
        return try {
            val skip = params.key ?: STARTING_KEY
            val limit = params.loadSize

            val response = safeApiCall {
                api.getProductList(limit = limit, skip = skip)
            }

//            if (skip > 0) {
//                delay(LOAD_DELAY_MILLIS.milliseconds)
//            }

            when (response) {
                is NetworkResult.Error -> {
                    return LoadResult.Error(Exception(response.error.toString()))
                }

                is NetworkResult.Success -> {
                    val body = response.data
                    val products = body.products.map { it.toDomainProduct() }

                    LoadResult.Page(
                        data = products,
                        prevKey = if (skip == STARTING_KEY) null else maxOf(
                            STARTING_KEY,
                            skip - limit,
                        ),
                        nextKey = if (products.isEmpty() || skip + products.size >= body.total) {
                            null
                        } else {
                            skip + products.size
                        },
                    )

                }
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Product>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(state.config.pageSize)
                ?: anchorPage?.nextKey?.minus(state.config.pageSize)
        }
    }
}