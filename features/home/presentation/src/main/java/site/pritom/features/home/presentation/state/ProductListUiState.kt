package site.pritom.features.home.presentation.state

/** Created by Pritom Dutta on 5/10/26 */
data class ProductListUiState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val isSuccess: Boolean = false
)
