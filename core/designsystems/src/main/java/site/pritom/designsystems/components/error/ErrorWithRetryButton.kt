package site.pritom.designsystems.components.error

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import site.pritom.designsystems.BrutalistContainer


@Composable
fun ErrorWithRetryButton(
    modifier: Modifier, onRetry: () -> Unit, message: String
) {

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(text = message, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.error)
        Spacer(modifier = Modifier.height(8.dp))

        BrutalistContainer(
            contentPadding = PaddingValues(horizontal = 50.dp, vertical = 8.dp),
            cornerRadius = 40.dp,
        ) {
            Button(
                onClick = onRetry,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent,
                    disabledContainerColor = Color.Transparent
                )
            ) {
                Text("Retry", color = Color.Black)
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ErrorWithRetryButtonPreview() {
    ErrorWithRetryButton(
        modifier = Modifier,
        onRetry = {},
        message = "Error"
    )
}