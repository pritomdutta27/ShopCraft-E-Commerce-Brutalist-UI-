package site.pritom.features.home.data.mapper

import site.prito.dutta.core.common.ext.firstLetterCapitalize
import site.pritom.features.home.data.model.ProductDto
import site.pritom.features.home.domain.model.Product
import java.text.DecimalFormat
import kotlin.math.ulp

/** Created by Pritom Dutta on 1/10/26 */

fun ProductDto.toDomainProduct(): Product {
    return Product(
        id = id,
        title = title,
        brand = brand ?: category.firstLetterCapitalize(),
        productImage = thumbnail,
        price = price,
        afterDiscountPrice = "%.2f".format(price - (price * (discountPercentage / 100))),
        totalComment = reviews.size,
        rating = rating,
        discountPercentage = discountPercentage,
        stock = stock,
        isAvailabilityProduct = availabilityStatus,
    )
}