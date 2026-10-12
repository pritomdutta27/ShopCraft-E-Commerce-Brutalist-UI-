package site.pritom.features.home.presentation.screen

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import site.pritom.features.home.presentation.screen.mobile.HomeScreenForPhone
import site.pritom.features.home.presentation.screen.tab.HomeScreenForTab
import site.pritom.features.home.presentation.viewmodel.HomeViewModel


@OptIn(ExperimentalMaterial3AdaptiveApi::class)
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
