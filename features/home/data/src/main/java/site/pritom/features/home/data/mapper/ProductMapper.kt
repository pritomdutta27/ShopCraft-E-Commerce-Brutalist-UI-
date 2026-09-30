package site.pritom.features.home.data.mapper

import site.pritom.features.home.data.model.ProductDto
import site.pritom.features.home.domain.model.Product

/** Created by Pritom Dutta on 1/10/26 */

fun ProductDto.toDomainProduct(): Product{
    return Product(
        title = title,
        productImage = thumbnail,
        price = price,
        rating = rating,
        discountPercentage = discountPercentage,
        stock = stock,
        isAvailabilityProduct = availabilityStatus
    )
}