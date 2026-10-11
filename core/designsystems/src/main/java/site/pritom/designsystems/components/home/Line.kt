package site.pritom.designsystems.components.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


@Composable
fun Line(lineColor: Color = Color.Black, lineWidth: Dp = 3.dp) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(lineWidth)
            .background(lineColor)
    )
}


@Preview(showBackground = true)
@Composable
fun LinePreview() {
}