package site.pritom.designsystems.components.home

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.palette.graphics.Palette
import coil.compose.AsyncImage
import site.pritom.designsystems.BrutalistContainer
import site.pritom.designsystems.R
import site.pritom.designsystems.components.discount.DiscountImageTopWithFavButton
import site.pritom.designsystems.components.discount.DiscountPriceText
import site.pritom.designsystems.components.rating.RatingWithStarAndCommentCount
import site.pritom.designsystems.utils.ImageUtils

@Composable
fun ProductGridItem(
    modifier: Modifier = Modifier,
    productImage: String = "",
    imageBackgroundColor: Int = 0,
    title: String = "",
    brandName: String = "",
    ratingTxt: Double = 0.0,
    totalComment: Int = 0,
    price: String = "",
    afterDiscountPrice: String = "",
    discountPercentage: String = "",
    isAvailable: String = "",
    quality: Int = 0,
) {

    var backgroundColor by remember {
        mutableStateOf(Color.White)
    }
    BrutalistContainer(
        modifier = Modifier.fillMaxWidth(),
    ) {

        Column {

            Box {

                AsyncImage(
                    model = productImage,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .background(backgroundColor),
                    contentScale = ContentScale.Crop,
                    onSuccess = {
                        val bitmap = (it.result.drawable as BitmapDrawable).bitmap.copy(
                            Bitmap.Config.ARGB_8888, true,
                        )
                        backgroundColor = ImageUtils.parseColorSwatch(
                            Palette.from(bitmap).generate().dominantSwatch,
                        )
                    },
                )

                DiscountImageTopWithFavButton(discountPercentage = discountPercentage)
            }


            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Black)
                    .height(3.dp),
            )


            Column(
                modifier = Modifier.padding(15.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp),
            ) {
                Text(
                    text = brandName,
                    style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Normal),
                    color = Color.Black,
                )

                Text(
                    text = title,
                    style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Black),
                    color = Color.Black,
                )

                RatingWithStarAndCommentCount(rating = ratingTxt, commentCount = totalComment)

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(2.dp)
                        .drawBehind(
                            onDraw = {
                                drawLine(
                                    start = Offset(0f, 0f),
                                    end = Offset(size.width, 0f),
                                    color = Color.Black.copy(alpha = 0.2f),
                                    strokeWidth = 6f,
                                    pathEffect = PathEffect.dashPathEffect(
                                        floatArrayOf(20f, 10f),
                                        0f,
                                    ),
                                )
                            },
                        ),
                )

                CircleWithText(text = "$quality $isAvailable")

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {

                    DiscountPriceText(
                        price = price,
                        afterDiscountPrice = afterDiscountPrice,
                    )

                    BrutalistContainer(
                        contentPadding = PaddingValues(2.dp),
                        backgroundColor = Color(0xFF9BA3EB),
                    ) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "Pluse",
                            tint = Color.Black,
                            modifier = Modifier.padding(4.dp),
                        )
                    }
                }
            }

        }

    }
}


@Preview(showBackground = true)
@Composable
fun ProductGridItemPreview() {
    ProductGridItem()
}