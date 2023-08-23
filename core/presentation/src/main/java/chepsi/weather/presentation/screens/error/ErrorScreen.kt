package chepsi.weather.presentation.screens.error

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import chepsi.weather.presentation.theme.WeatherAppTheme

@Composable
fun ErrorScreen(
    errorMessage: String,
    retryButtonText: String,
    onRetryAction: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = errorMessage,
            textAlign = TextAlign.Center
        )
        Button(onClick = { onRetryAction() }) {
            Text(text = retryButtonText)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ErrorScreenPreview() {
    WeatherAppTheme {
        ErrorScreen(
            errorMessage = "We need location Permissions to get current location",
            retryButtonText = "Request Permissions",
            onRetryAction = {}
        )
    }
}