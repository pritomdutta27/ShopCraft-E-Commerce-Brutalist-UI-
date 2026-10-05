package site.pritom.features.home.domain.repository

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import site.pritom.features.home.domain.model.Product

/** Created by Pritom Dutta on 1/10/26 */
interface ProductRepository {

    fun getProducts(): Flow<PagingData<Product>>
}