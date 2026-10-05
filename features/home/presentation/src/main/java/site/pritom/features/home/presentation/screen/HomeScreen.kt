package site.pritom.features.home.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.core.graphics.drawable.toBitmap
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.palette.graphics.Palette
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.request.SuccessResult
import site.pritom.designsystems.BrutalistContainer
import site.pritom.designsystems.components.home.CategoryHome
import site.pritom.designsystems.components.home.FilterItem
import site.pritom.designsystems.components.home.SubHome
import site.pritom.designsystems.components.home_toolbar.HomeToolBar
import site.pritom.designsystems.components.search.SearchComponent
import site.pritom.features.home.domain.model.Product
import site.pritom.features.home.presentation.viewmodel.HomeViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState
        .collectAsStateWithLifecycle()

    val products = uiState.products
        .collectAsLazyPagingItems()

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(Color(0xFFF9F9FF)),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        HomeToolBar()

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(2.dp)
                .background(Color.Black)
        )

        LazyColumn{

            item {
                SearchComponent(
                    modifier = Modifier
                        .padding(start = 15.dp, end = 15.dp)
                )

                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(12.dp)
                )

                SubHome()

                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(12.dp)
                )

                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    contentPadding = PaddingValues(
                        start = 16.dp,
                        end = 16.dp
                    ),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    item{
                        FilterItem()
                    }

                    items(4){
                        CategoryHome()
                    }
                }


                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(16.dp)
                )
            }

            items(
                count = products.itemCount,
                key = { index ->
                    products[index]?.id ?: index
                }
            ) { index ->
                products[index]?.let { product ->
                    ProductItem(
                        product,
                        onClick ={ }
                    )
                }
            }
        }
    }

}

@Composable
fun ProductItem(
    product: Product,
    onClick: (Product) -> Unit,
    modifier: Modifier = Modifier
) {

    val backgroundColor = rememberDominantColor(
        product.productImage
    )

    Column {


        Card(
            modifier = modifier
                .fillMaxWidth()
                .clickable {
                    onClick(product)
                }
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                AsyncImage(
                    model = product.productImage,
                    contentDescription = product.title,
                    modifier = Modifier
                        .size(100.dp)
                        .background(backgroundColor)
                        .clip(RoundedCornerShape(12.dp)),
                    contentScale = ContentScale.Crop
                )

                Spacer(
                    modifier = Modifier.width(16.dp)
                )

                Column(
                    modifier = Modifier.weight(1f)
                ) {

                    Text(
                        text = product.title,
                        style = MaterialTheme.typography.displayMedium,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )

                    Spacer(
                        modifier = Modifier.height(4.dp)
                    )

                    Text(
                        text = product.price.toString(),
                        style = MaterialTheme.typography.bodyMedium,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )

                    Spacer(
                        modifier = Modifier.height(8.dp)
                    )

                    Text(
                        text = "$${product.price}",
                        style = MaterialTheme.typography.titleSmall
                    )
                }
            }
        }
    }

}

@Composable
fun rememberDominantColor(
    imageUrl: String
): Color {

    val context = LocalContext.current

    var color by remember(imageUrl) {
        mutableStateOf(Color.LightGray)
    }

    LaunchedEffect(imageUrl) {

        val imageLoader = ImageLoader(context)

        val request = ImageRequest.Builder(context)
            .data(imageUrl)
            .allowHardware(false)
            .build()

        val result = imageLoader.execute(request)

        if (result is SuccessResult) {

            val bitmap = result.drawable.toBitmap()

            val palette = Palette
                .from(bitmap)
                .maximumColorCount(16)
                .generate()

            color = Color(
                palette.getDominantColor(
                    android.graphics.Color.LTGRAY
                )
            )
        }
    }

    return color
}