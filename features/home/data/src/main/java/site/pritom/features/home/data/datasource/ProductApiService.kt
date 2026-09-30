package site.pritom.features.home.data.datasource

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import site.pritom.features.home.data.model.ResponseProductList

/** Created by Pritom Dutta on 1/10/26 */
interface ProductApiService {
    @GET("/products")
    suspend fun getProductList(
        @Query("limit") limit: Int = 10,
        @Query("skip") skip: Int = 0
    ): Response<ResponseProductList>
}