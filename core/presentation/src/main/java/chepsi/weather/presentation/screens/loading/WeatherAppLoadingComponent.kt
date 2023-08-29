package chepsi.weather.presentation.screens.loading

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import chepsi.weather.presentation.theme.WeatherAppTheme

@Composable
fun WeatherAppLoadingComponent() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 30.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator()
    }
}

@Preview
@Composable
private fun WeatherAppLoadingComponentPreview() {
    WeatherAppTheme {
        WeatherAppLoadingComponent()
    }
}