package site.pritom.features.home.domain.model

/** Created by Pritom Dutta on 1/10/26 */
data class Product(
    val title: String,
    val productImage: String,
    val price: Double,
    val rating: Double,
    val discountPercentage: Double,
    val stock: Int,
    val isAvailabilityProduct: String,
)
