package site.pritom.designsystems

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.milliseconds


@Composable
fun BrutalistContainer(
    modifier: Modifier = Modifier,
    backgroundColor: Color = Color.White,
    borderColor: Color = Color.Black,
    shadowColor: Color = Color.Black,
    borderWidth: Dp = 2.dp,
    cornerRadius: Dp = 12.dp,
    shadowOffset: DpOffset = DpOffset(3.dp, 3.dp),
    contentPadding: PaddingValues = PaddingValues(0.dp),
    contentAlignment: Alignment = Alignment.Center,
    isShowShadow: Boolean = true,
    enabled: Boolean = false,
    onClickItem: () -> Unit = {},
    content: @Composable BoxScope.() -> Unit,
) {
    val shape = RoundedCornerShape(cornerRadius)

    var isClicked by remember {
        mutableStateOf(false)
    }

    val scope = rememberCoroutineScope()
    var resetJob by remember {
        mutableStateOf<Job?>(null)
    }

    val shadowProgress by animateFloatAsState(
        targetValue = if (isClicked) 0f else 1f,
        animationSpec = tween(
            durationMillis = 200,
            easing = FastOutSlowInEasing,
        ),
        label = "shadowAnimation",
    )

    val animatedOffsetX = shadowOffset.x * shadowProgress
    val animatedOffsetY = shadowOffset.y * shadowProgress

    Box(
        modifier = modifier
            .drawBehind {
                if (isShowShadow) {
                    drawRoundRect(
                        color = shadowColor,
                        topLeft = Offset(
                            x = animatedOffsetX.toPx(),
                            y = animatedOffsetY.toPx(),
                        ),
                        size = size,
                        cornerRadius = CornerRadius(cornerRadius.toPx()),
                    )
                }
            }
            .clip(shape)
            .background(backgroundColor)
            .border(
                width = borderWidth,
                color = borderColor,
                shape = shape,
            )
            .clickable(
                enabled = enabled,
                indication = null,
                interactionSource = null,
            ) {
                isClicked = true
                resetJob?.cancel()

                resetJob = scope.launch {
                    delay(220.milliseconds)
                    isClicked = false
                }

                onClickItem()
            }
            .padding(contentPadding),
        contentAlignment = contentAlignment,
        content = content,
    )
}


@Preview(showBackground = true)
@Composable
fun CommonShadowBrutalistPreview(){
    BrutalistContainer(){
        Text(text = "CommonShadowBrutalistPreview", modifier = Modifier.padding(16.dp))
    }
}