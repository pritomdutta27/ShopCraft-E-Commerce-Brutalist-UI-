package site.pritom.features.home.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
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
import site.pritom.designsystems.components.home.ProductGridItem
import site.pritom.designsystems.components.home.SubHome
import site.pritom.designsystems.components.home_toolbar.HomeToolBar
import site.pritom.designsystems.components.search.SearchComponent
import site.pritom.features.home.domain.model.Product
import site.pritom.features.home.presentation.viewmodel.HomeViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState
        .collectAsStateWithLifecycle()

    val products = uiState.products
        .collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            HomeToolBar()
        },
    ) { paddingValue ->
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(paddingValue),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(
                bottom = 16.dp,
            ),
        ) {

            // Search
            item(

                span = {
                    GridItemSpan(maxLineSpan)
                },
            ) {

                SearchComponent(
                    modifier = Modifier
                        .padding(horizontal = 15.dp)
                        .padding(top = 20.dp),
                )
            }

            // SubHome
            item(
                span = {
                    GridItemSpan(maxLineSpan)
                },
            ) {
                SubHome()
            }

            // Categories
            item(
                span = {
                    GridItemSpan(maxLineSpan)
                },
            ) {
                LazyRow(
                    contentPadding = PaddingValues(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                ) {

                    item {
                        FilterItem()
                    }

                    items(4) {
                        CategoryHome()
                    }
                }
            }

            // Products
            items(
                count = products.itemCount,
                key = { index ->
                    products[index]?.id ?: "placeholder-$index"
                },
            ) { index ->
                Box(
                    modifier = Modifier.padding(
                        start = if (index % 2 == 0) 15.dp else 0.dp,
                        end = if (index % 2 == 1) 15.dp else 0.dp,
                    ),
                ) {
                    products[index]?.let { product ->
                        ProductGridItem()
                    }
                }
            }
        }
    }
}


@Composable
fun rememberDominantColor(
    imageUrl: String,
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
                    android.graphics.Color.LTGRAY,
                ),
            )
        }
    }

    return color
}