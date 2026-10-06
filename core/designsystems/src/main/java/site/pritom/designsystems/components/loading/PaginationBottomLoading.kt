package site.pritom.designsystems.components.loading

import androidx.compose.animation.graphics.res.animatedVectorResource
import androidx.compose.animation.graphics.res.rememberAnimatedVectorPainter
import androidx.compose.animation.graphics.vector.AnimatedImageVector
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import site.pritom.designsystems.BrutalistContainer
import site.pritom.designsystems.R
import kotlin.time.Duration.Companion.milliseconds


@Composable
fun PaginationBottomLoading(
    modifier: Modifier = Modifier,
    loadingText: String = "Load More Products"
) {

    val image = AnimatedImageVector.animatedVectorResource(R.drawable.item_loading_anim)
    // 2. Track whether the animation is at the start (false) or end (true) state
    var atEnd by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        while (true) {
            atEnd = !atEnd
            // Adjust according to your AVD duration
            delay(500.milliseconds)
        }
    }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        BrutalistContainer(
            modifier = Modifier.size(40.dp),
            contentPadding = PaddingValues(2.dp),
        ) {
            Image(
                painter = rememberAnimatedVectorPainter(image, atEnd),
                contentDescription = "Shopping",
                modifier = Modifier
                    .padding(4.dp)
                    .fillMaxSize()
            )
        }

        BrutalistContainer(
            modifier = Modifier.padding(top = 8.dp),
            backgroundColor = Color(0xFF9BA3EB),
            contentPadding = PaddingValues(horizontal = 10.dp, vertical = 5.dp),
        ) {
            Text(
                text = loadingText,
                style = MaterialTheme.typography.labelLarge,
                color = Color.Black
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PaginationBottomLoadingPreview() {
    PaginationBottomLoading()
}