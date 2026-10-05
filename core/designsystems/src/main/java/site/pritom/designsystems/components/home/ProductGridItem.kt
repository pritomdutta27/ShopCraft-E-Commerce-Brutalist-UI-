package site.pritom.designsystems.components.home

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import site.pritom.designsystems.BrutalistContainer
import site.pritom.designsystems.R
import site.pritom.designsystems.components.discount.DiscountImageTopWithFavButton
import site.pritom.designsystems.components.discount.DiscountPriceText
import site.pritom.designsystems.components.rating.RatingWithStarAndCommentCount

@Composable
fun ProductGridItem() {
    BrutalistContainer(
        modifier = Modifier.fillMaxWidth(),
    ) {

        Column {

            Box {

                Image(
                    painter = painterResource(id = R.drawable.demo),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    contentScale = ContentScale.Crop,
                )

                DiscountImageTopWithFavButton()
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
                    text = "AURA GLOW",
                    style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Normal),
                    color = Color.Black,
                )

                Text(
                    text = "Essence Mascara…",
                    style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Black),
                    color = Color.Black,
                )

                RatingWithStarAndCommentCount()

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

                CircleWithText(text = "99 available")

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {

                    DiscountPriceText()

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