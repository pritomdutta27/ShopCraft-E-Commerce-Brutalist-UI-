package site.pritom.features.home.domain.model

/** Created by Pritom Dutta on 1/10/26 */
data class Product(
    val id: Int,
    val title: String,
    val brand: String = "",
    val productImage: String,
    val price: Double,
    val afterDiscountPrice: String,
    val rating: Double,
    val discountPercentage: Double,
    val backgroundColor: Int = 0,
    val stock: Int,
    val totalComment: Int,
    val isAvailabilityProduct: String,
)
