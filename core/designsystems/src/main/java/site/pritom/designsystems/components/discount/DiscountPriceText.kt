package site.pritom.designsystems.components.discount

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import site.pritom.designsystems.R

/** Created by Pritom Dutta on 5/10/26 */

@Composable
fun DiscountPriceText() {
    Row(
        horizontalArrangement = Arrangement.spacedBy(3.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "$24.00",
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Black),
            color = Color.Black
        )

        Text(
            text = "$80.00",
            color = Color.Black.copy(alpha = 0.5f),
            style = MaterialTheme.typography.labelSmall,
            textDecoration = TextDecoration.LineThrough
        )
    }

}

@Preview(showBackground = true)
@Composable
fun DiscountPriceTextPreview() {
    DiscountPriceText()
}