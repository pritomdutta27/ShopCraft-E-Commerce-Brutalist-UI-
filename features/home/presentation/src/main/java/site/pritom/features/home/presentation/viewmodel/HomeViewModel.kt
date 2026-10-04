package site.pritom.features.home.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import site.pritom.features.home.domain.model.Product
import site.pritom.features.home.domain.usecase.GetProductListUseCase
import site.pritom.features.home.presentation.state.ProductListUiState
import javax.inject.Inject
import kotlin.time.Duration.Companion.milliseconds

/** Created by Pritom Dutta on 1/10/26 */
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getProductListUseCase: GetProductListUseCase,
) : ViewModel() {
    private val _uiState = MutableStateFlow(ProductListUiState())
    val uiState: StateFlow<ProductListUiState> = _uiState.asStateFlow()

    val products: Flow<PagingData<Product>> =
        getProductListUseCase()
            .cachedIn(viewModelScope)

    init {
        viewModelScope.launch {
            observeProducts()
        }
    }

    fun observeProducts() {

        getProductListUseCase()
            .cachedIn(viewModelScope)
            .onStart {
                _uiState.update {
                    it.copy(
                        isLoading = true,
                        errorMessage = null,
                        isSuccess = false,
                    )
                }
            }.onEach { pagingData ->
                _uiState.update {
                    it.copy(
                        products = flow { emit(pagingData) },
                        isLoading = false,
                        isSuccess = true,
                    )
                }
            }.catch { throwable ->
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        isSuccess = false,
                        errorMessage = throwable.message,
                    )
                }
            }.launchIn(viewModelScope)
    }
}