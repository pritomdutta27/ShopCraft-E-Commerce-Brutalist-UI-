package site.pritom.designsystems.components.home

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import site.pritom.designsystems.BrutalistContainer

@Composable
fun SubHome() {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 15.dp, end = 15.dp)
    ) {

        CircleWithText(text = "REST API /PRODUCTS")

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(12.dp)
        )

        Text(
            "All Products",
            color = Color.Black,
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(12.dp)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {

            Text(
                text = "Discover instant dummy JSON catalog\n" +
                        "data",
                style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Normal),
                color = Color.Black.copy(alpha = 0.6f)
            )

            BrutalistContainer(
                contentPadding = PaddingValues(10.dp)
            ) {

                Row(
                    horizontalArrangement = Arrangement.spacedBy(2.dp),
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
                        text = "194 items",
                        style = MaterialTheme.typography.labelSmall,
                        color = Color.Black
                    )
                }

            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun SubHomePreview() {
    SubHome()
}