package site.pritom.features.home.presentation.screen

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import site.pritom.designsystems.components.home.CategoryHome
import site.pritom.designsystems.components.home.FilterItem
import site.pritom.designsystems.components.home.ProductGridItem
import site.pritom.designsystems.components.home.SubHome
import site.pritom.designsystems.components.home_toolbar.HomeToolBar
import site.pritom.designsystems.components.loading.PaginationBottomLoading
import site.pritom.designsystems.components.loading.space.SpaceRefreshIndicator
import site.pritom.designsystems.components.search.SearchComponent
import site.pritom.features.home.presentation.viewmodel.HomeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState
        .collectAsStateWithLifecycle()

    val products = uiState.products
        .collectAsLazyPagingItems()

    val pullToRefreshState = rememberPullToRefreshState()

    val isRefreshing =
        products.loadState.refresh is LoadState.Loading

    val refreshHeight = 150.dp

    val density = LocalDensity.current

    /*
     * How much the content should move down.
     *
     * During pull:
     *      distanceFraction = 0f -> 0%
     *      distanceFraction = 1f -> 100%
     *
     * During actual refresh:
     *      keep the refresh area visible.
     */
    val contentOffset by animateDpAsState(
        targetValue = when {
            isRefreshing -> 80.dp
            pullToRefreshState.distanceFraction > 0f -> {
                refreshHeight *
                        pullToRefreshState.distanceFraction.coerceIn(
                            0f,
                            1f,
                        )
            }
            else -> 0.dp
        },
        animationSpec = tween(
            durationMillis = 200,
        ),
        label = "refresh_content_offset",
    )

    Scaffold(
        topBar = {
            HomeToolBar()
        },
    ) { paddingValue ->

        PullToRefreshBox(
            state = pullToRefreshState,
            isRefreshing = isRefreshing,
            onRefresh = {
                products.refresh()
            },
            modifier = Modifier
                .fillMaxSize(),
            indicator = {
                if (isRefreshing) {
                    SpaceRefreshIndicator(
                        isRefreshing = true,
                        height = refreshHeight,
                    )
                }
            },
        ) {

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),

                modifier = modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .padding(paddingValue)
                    .offset {
                        IntOffset(
                            x = 0,
                            y = with(density) {
                                contentOffset.roundToPx()
                            },
                        )
                    },

                horizontalArrangement = Arrangement.spacedBy(16.dp),

                verticalArrangement = Arrangement.spacedBy(16.dp),

                contentPadding = PaddingValues(
                    bottom = 16.dp,
                ),
            ) {

                // -----------------------------------------------------
                // Search
                // -----------------------------------------------------

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

                // -----------------------------------------------------
                // Sub Home
                // -----------------------------------------------------

                item(
                    span = {
                        GridItemSpan(maxLineSpan)
                    },
                ) {
                    SubHome()
                }

                // -----------------------------------------------------
                // Categories
                // -----------------------------------------------------

                item(
                    span = {
                        GridItemSpan(maxLineSpan)
                    },
                ) {
                    LazyRow(
                        contentPadding = PaddingValues(
                            horizontal = 16.dp,
                        ),
                        horizontalArrangement = Arrangement.spacedBy(
                            10.dp,
                        ),
                    ) {
                        item {
                            FilterItem()
                        }

                        items(4) {
                            CategoryHome()
                        }
                    }
                }

                // -----------------------------------------------------
                // Products
                // -----------------------------------------------------

                items(
                    count = products.itemCount,
                    key = { index ->
                        products[index]?.id
                            ?: "placeholder-$index"
                    },
                ) { index ->

                    val product = products[index]

                    Box(
                        modifier = Modifier.padding(
                            start = if (index % 2 == 0) {
                                15.dp
                            } else {
                                0.dp
                            },
                            end = if (index % 2 == 1) {
                                15.dp
                            } else {
                                0.dp
                            },
                        ),
                    ) {
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

                // -----------------------------------------------------
                // Pagination Loading
                // -----------------------------------------------------

                when (products.loadState.append) {

                    is LoadState.Loading -> {

                        item(
                            span = {
                                GridItemSpan(maxLineSpan)
                            },
                        ) {
                            PaginationBottomLoading(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                            )
                        }
                    }

                    is LoadState.Error -> {

                        item(
                            span = {
                                GridItemSpan(maxLineSpan)
                            },
                        ) {
                            PaginationError(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                                onRetry = {
                                    products.retry()
                                },
                            )
                        }
                    }

                    is LoadState.NotLoading -> Unit
                }
            }
        }
    }
}

@Composable
fun PaginationError(modifier: Modifier, onRetry: () -> Unit) {
   // TODO("Not yet implemented")
}