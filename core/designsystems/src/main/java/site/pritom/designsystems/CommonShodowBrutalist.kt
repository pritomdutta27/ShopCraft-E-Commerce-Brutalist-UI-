package site.pritom.designsystems

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
    content: @Composable BoxScope.() -> Unit
) {
    val shape = RoundedCornerShape(cornerRadius)

    Box(
        modifier = modifier
            .drawBehind {
                if (isShowShadow) {
                    drawRoundRect(
                        color = shadowColor,
                        topLeft = Offset(
                            x = shadowOffset.x.toPx(),
                            y = shadowOffset.y.toPx()
                        ),
                        size = size,
                        cornerRadius = CornerRadius(
                            cornerRadius.toPx()
                        )
                    )
                }
            }
            .clip(shape)
            .background(backgroundColor)
            .border(
                width = borderWidth,
                color = borderColor,
                shape = shape
            )
            .padding(contentPadding),
        contentAlignment = contentAlignment,
        content = content
    )
}

@Preview(showBackground = true)
@Composable
fun CommonShadowBrutalistPreview(){
    BrutalistContainer(){
        Text(text = "CommonShadowBrutalistPreview", modifier = Modifier.padding(16.dp))
    }
}