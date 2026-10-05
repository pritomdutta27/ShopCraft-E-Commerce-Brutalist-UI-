package site.pritom.features.home.domain.usecase

import site.pritom.features.home.domain.repository.ProductRepository
import javax.inject.Inject

/** Created by Pritom Dutta on 1/10/26 */
class GetProductListUseCase @Inject constructor(
    private val repository: ProductRepository
) {
    operator fun invoke() = repository.getProducts()
}