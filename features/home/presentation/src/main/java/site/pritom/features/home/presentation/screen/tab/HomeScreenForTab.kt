package site.pritom.features.home.presentation.screen.tab

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.unit.dp
import site.pritom.designsystems.components.home_toolbar.HomeToolBar
import site.pritom.features.home.presentation.viewmodel.HomeViewModel

@Composable
fun HomeScreenForTab(
    homeViewModel: HomeViewModel
) {

    Scaffold(
        topBar = {
            HomeToolBar(isShowSearchField = true)
        },
    ) { paddingValue ->

        Row(
            modifier = Modifier
                .padding(top = paddingValue.calculateTopPadding())
                .padding(horizontal = 16.dp, vertical = 16.dp)
                .fillMaxSize(),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
        ) {


            LeftSide(modifier = Modifier.weight(1f))

            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(2.dp)
                    .drawBehind {
                        drawLine(
                            start = Offset(size.width / 2f, 0f),
                            end = Offset(size.width / 2f, size.height),
                            color = Color.Black.copy(alpha = 0.2f),
                            strokeWidth = 2.dp.toPx(),
                            pathEffect = PathEffect.dashPathEffect(
                                floatArrayOf(
                                    6.dp.toPx(),
                                    4.dp.toPx(),
                                ),
                            ),
                        )
                    },
            )

            RightSide(modifier = Modifier.weight(1f), viewModel = homeViewModel)

        }
    }
}