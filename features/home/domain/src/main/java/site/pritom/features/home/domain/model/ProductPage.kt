package site.pritom.features.home.domain.model

data class ProductPage(
    val products: List<Product>,
    val skip: Int,
    val limit: Int,
    val total: Int
)
