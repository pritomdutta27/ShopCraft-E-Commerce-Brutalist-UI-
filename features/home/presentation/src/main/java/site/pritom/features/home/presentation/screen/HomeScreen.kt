package site.pritom.features.home.presentation.screen

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import site.pritom.features.home.presentation.screen.mobile.HomeScreenForPhone
import site.pritom.features.home.presentation.screen.tab.HomeScreenForTab
import site.pritom.features.home.presentation.viewmodel.HomeViewModel


@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
) {

    val adaptiveInfo = currentWindowAdaptiveInfo()
    val widthSizeClass = adaptiveInfo.windowSizeClass.widthSizeClass


    when (widthSizeClass) {
        WindowWidthSizeClass.Compact -> {
            HomeScreenForPhone(viewModel = viewModel)
        }
        WindowWidthSizeClass.Medium -> {
            HomeScreenForTab(viewModel)
        }
        WindowWidthSizeClass.Expanded -> {
            HomeScreenForTab(viewModel)
        }
    }
}

@Composable
fun PaginationError(modifier: Modifier, onRetry: () -> Unit) {
   // TODO("Not yet implemented")
}