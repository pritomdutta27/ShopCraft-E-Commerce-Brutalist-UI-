package site.pritom.features.home.presentation.screen.tab

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.compose.collectAsLazyPagingItems
import site.pritom.designsystems.BrutalistContainer
import site.pritom.designsystems.components.home.ProductGridItem
import site.pritom.features.home.presentation.viewmodel.HomeViewModel


@Composable
fun RightSide(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel,
) {

    val uiState by viewModel.uiState
        .collectAsStateWithLifecycle()

    val products = uiState.products
        .collectAsLazyPagingItems()

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(15.dp),
        horizontalAlignment = Alignment.Start
    ) {

        BrutalistContainer(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(10.dp)
        ) {

            Text(
                "All Products",
                color = Color.Black,
                style = MaterialTheme.typography.titleLarge
            )
        }
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),

            modifier = Modifier
                .fillMaxSize()
                .background(Color.White),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {

            items(
                count = products.itemCount,
                key = { index ->
                    products[index]?.id
                        ?: "placeholder-$index"
                },
            ) { index ->

                val product = products[index]

                product?.let {

                    ProductGridItem(
                        productImage = it.productImage,
                        imageBackgroundColor = it.backgroundColor,
                        title = it.title,
                        brandName = it.brand,
                        ratingTxt = it.rating,
                        totalComment = it.totalComment,
                        discountPercentage =
                            it.discountPercentage.toString(),
                        price = it.price.toString(),
                        afterDiscountPrice =
                            it.afterDiscountPrice,
                        quality = it.stock,
                        isAvailable =
                            it.isAvailabilityProduct,
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun RightSidePreview() {

}