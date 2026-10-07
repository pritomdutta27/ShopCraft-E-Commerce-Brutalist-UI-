package site.pritom.features.home.presentation.screen.tab

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import site.pritom.designsystems.BrutalistContainer
import site.pritom.designsystems.components.home.CategoryHome
import site.pritom.designsystems.components.home.CircleWithText
import site.pritom.designsystems.components.home.FilterItem
import site.pritom.designsystems.components.home.ItemCountComponentForTab
import site.pritom.designsystems.components.home.Line


@Composable
fun LeftSide(
    modifier: Modifier = Modifier
) {

    BrutalistContainer(
        modifier = modifier,
        contentPadding = PaddingValues(20.dp)
    ) {

        Column(
            verticalArrangement = Arrangement.spacedBy(15.dp),
            horizontalAlignment = Alignment.Start
        ){
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
                CircleWithText(text = "REST API /PRODUCTS")

                ItemCountComponentForTab()
            }

            Line(lineWidth = 2.dp)

            Text(
                text = "Discover instant dummy JSON catalog data",
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Normal),
                color = Color.Black.copy(alpha = 0.6f)
            )

            LazyVerticalStaggeredGrid(
                columns = StaggeredGridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(
                    10.dp,
                ),
                verticalItemSpacing = 10.dp,
            ) {
                item {
                    FilterItem()
                }

                items(4) {
                    CategoryHome()
                }
            }
        }


    }
}


@Preview(showBackground = true)
@Composable
fun LeftSidePreview() {
    LeftSide()
}