package site.pritom.designsystems.components.home

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
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
import site.pritom.designsystems.BrutalistContainer


@Composable
fun ItemCountComponent() {
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

@Preview(showBackground = true)
@Composable
fun ItemCountComponentPreview() {
    ItemCountComponent()
}


@Composable
fun ItemCountComponentForTab(){

    BrutalistContainer(
        contentPadding = PaddingValues(horizontal = 10.dp, vertical = 2.dp),
        backgroundColor = Color(0xFF9BA3EB)
    ) {

        Row(
            horizontalArrangement = Arrangement.spacedBy(2.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = "194 items",
                style = MaterialTheme.typography.labelSmall,
                color = Color.Black
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ItemCountComponentForTabPreview() {
    ItemCountComponentForTab()
}