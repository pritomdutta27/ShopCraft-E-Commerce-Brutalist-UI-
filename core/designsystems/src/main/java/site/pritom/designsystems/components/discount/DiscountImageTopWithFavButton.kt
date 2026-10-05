package site.pritom.designsystems.components.discount

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import site.pritom.designsystems.BrutalistContainer

/** Created by Pritom Dutta on 5/10/26 */

@Composable
fun DiscountImageTopWithFavButton(
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .padding(10.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {

        BrutalistContainer(
            contentPadding = PaddingValues(horizontal = 12.dp, vertical = 8.dp),
            cornerRadius = 40.dp,
            backgroundColor = Color(0xFFFFDADB),
        ) {
            Text(
                "10.48% OFF",
                style = MaterialTheme.typography.labelSmall,
                color = Color.Black,
            )
        }

        BrutalistContainer(
            contentPadding = PaddingValues(2.dp),
        ) {
            Icon(
                imageVector = Icons.Default.FavoriteBorder,
                contentDescription = "Favorite",
                tint = Color.Black,
                modifier = Modifier.padding(4.dp),
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DiscountImageTopWithFavButtonPreview() {
    DiscountImageTopWithFavButton()
}

