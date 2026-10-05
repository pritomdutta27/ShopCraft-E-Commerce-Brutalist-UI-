package site.pritom.designsystems.components.rating

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import site.pritom.designsystems.R

/** Created by Pritom Dutta on 5/10/26 */

@Composable
fun RatingWithStarAndCommentCount() {
    Row(
        horizontalArrangement = Arrangement.spacedBy(3.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Icon(
            painter = painterResource(id = R.drawable.rating_star),
            contentDescription = "Rating Star",
        )
        Text(
            text = "2.56",
            style = MaterialTheme.typography.labelMedium,
            color = Color.Black
        )

        Text(
            text = "(3)",
            color = Color.Black.copy(alpha = 0.5f),
            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Normal)
        )
    }
}


@Preview(showBackground = true)
@Composable
fun RatingWithStarAndCommentCountPreview(){
    RatingWithStarAndCommentCount()
}