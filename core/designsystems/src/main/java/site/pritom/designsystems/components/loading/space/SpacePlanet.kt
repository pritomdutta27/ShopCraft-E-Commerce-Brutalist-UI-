package site.pritom.designsystems.components.loading.space

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.graphics.res.animatedVectorResource
import androidx.compose.animation.graphics.res.rememberAnimatedVectorPainter
import androidx.compose.animation.graphics.vector.AnimatedImageVector
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.pulltorefresh.PullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import site.pritom.designsystems.R
import kotlin.time.Duration.Companion.milliseconds

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SpaceRefreshIndicator(
    isRefreshing: Boolean,
    height: Dp,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(height)
            .background(
                Color(0xFFFFFFFF),
            ),
        contentAlignment = Alignment.Center,
    ) {
        SpacePlanet(
            isRefreshing = isRefreshing,
        )
    }
}

@Composable
private fun SpacePlanet(
    isRefreshing: Boolean,
) {
    val image = AnimatedImageVector.animatedVectorResource(
        R.drawable.item_loading_anim,
    )

    var atEnd by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(isRefreshing) {

        if (isRefreshing) {
            while (true) {
                atEnd = !atEnd
                delay(500.milliseconds)
            }

        } else {
            atEnd = false
        }
    }

    Image(
        painter = rememberAnimatedVectorPainter(
            animatedImageVector = image,
            atEnd = atEnd,
        ),
        contentDescription = "Refreshing",
        modifier = Modifier.padding(top = 50.dp).size(40.dp),
    )
}