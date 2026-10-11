package site.pritom.designsystems.components.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import site.pritom.designsystems.BrutalistContainer

@Composable
fun SearchComponent(
    modifier: Modifier = Modifier,
    isShowShadow: Boolean = true,
) {

    BrutalistContainer(
        modifier = modifier,
        isShowShadow = isShowShadow
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(start = 15.dp, end = 15.dp).height(50.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search"
            )

            TextField(
                value = "",
                onValueChange = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color.Transparent),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    disabledContainerColor = Color.White,
                    errorContainerColor = Color.White
                ),
                placeholder = {
                    Text(
                        text = "Search products, brands, tags...",
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            )
        }

    }

}

@Preview(showBackground = true)
@Composable
fun SearchComponentPreview() {
    SearchComponent()
}