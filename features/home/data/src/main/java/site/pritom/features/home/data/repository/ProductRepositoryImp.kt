package site.pritom.features.home.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import site.pritom.features.home.data.datasource.ProductRemoteDataSource
import site.pritom.features.home.data.paging.ProductsPagingSource
import site.pritom.features.home.domain.model.Product
import site.pritom.features.home.domain.repository.ProductRepository
import javax.inject.Inject

/** Created by Pritom Dutta on 1/10/26
 * https://codewithmandyal.medium.com/implementing-paging-3-with-clean-architecture-mvvm-hilt-coroutines-and-jetpack-compose-in-ea7f068d483d
 * */

class ProductRepositoryImpl @Inject constructor(
    private val remoteDataSource: ProductRemoteDataSource
) : ProductRepository {
    override fun getProducts(): Flow<PagingData<Product>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                ProductsPagingSource(remoteDataSource)
            }
        ).flow
    }
}