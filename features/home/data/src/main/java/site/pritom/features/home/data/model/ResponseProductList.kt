package site.pritom.features.home.data.model

data class ResponseProductList(
    val limit: Int,
    val products: List<ProductDto>,
    val skip: Int,
    val total: Int
)