package site.pritom.designsystems.components.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import site.pritom.designsystems.BrutalistContainer


@Composable
fun CategoryHome() {
    BrutalistContainer(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 10.dp),
    ) {

        Row(
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Categories",
                style = MaterialTheme.typography.titleMedium,
                color = Color.Black
            )

            Text(
                text = "(Beauty, Home)",
                color = Color.Black.copy(alpha = 0.5f),
                style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Normal)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CategoryHomePreview() {
    CategoryHome()
}