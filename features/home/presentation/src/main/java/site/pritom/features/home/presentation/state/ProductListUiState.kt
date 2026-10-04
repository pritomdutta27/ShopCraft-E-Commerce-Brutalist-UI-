package site.pritom.features.home.presentation.state

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import site.pritom.features.home.domain.model.Product

/** Created by Pritom Dutta on 5/10/26 */
data class ProductListUiState(
    val products: Flow<PagingData<Product>> = emptyFlow(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val isSuccess: Boolean = false
)
