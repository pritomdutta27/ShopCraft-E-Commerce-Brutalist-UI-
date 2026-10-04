package site.pritom.features.home.data.datasource

import javax.inject.Inject

class ProductRemoteDataSource @Inject constructor(
    private val productApiService: ProductApiService
) {

    suspend fun getProductList(limit: Int = 10, skip: Int = 0) =
        productApiService.getProductList(limit, skip)
}