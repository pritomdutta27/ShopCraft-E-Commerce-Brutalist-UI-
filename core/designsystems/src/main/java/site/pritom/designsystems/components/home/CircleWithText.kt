package site.pritom.designsystems.components.home

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/** Created by Pritom Dutta on 5/10/26 */

@Composable
fun CircleWithText(
    modifier: Modifier = Modifier,
    text: String,
) {
    Row(
        horizontalArrangement = Arrangement.Absolute.spacedBy(6.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier = Modifier
                .size(10.dp)
                .clip(RoundedCornerShape(50))
                .background(Color(0xFF62FAE3))
                .border(1.dp, Color.Black, RoundedCornerShape(50))
        )

        Text(
            text = text,
            style = MaterialTheme.typography.labelSmall,
            color = Color.Black
        )
    }
}


@Preview(showBackground = true)
@Composable
fun CircleWithTextPreview() {
    CircleWithText(text = "Sample Text")
}