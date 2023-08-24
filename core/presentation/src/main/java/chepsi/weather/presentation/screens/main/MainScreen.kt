package chepsi.weather.presentation.screens.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import chepsi.weather.presentation.R
import chepsi.weather.presentation.theme.WeatherAppTheme

@Composable
fun MainScreen(mainScreenViewModel: MainViewModel = hiltViewModel()) {
    LaunchedEffect(key1 = Unit) {
        mainScreenViewModel.onFetchMainScreenDetails()
    }
    val screenState = mainScreenViewModel.mainScreenState

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(screenState.currentWeather.backgroundColor)
    ) {
        Box(modifier = Modifier.fillMaxHeight(0.5f)) {
            Image(
                painter = painterResource(id = screenState.currentWeather.image),
                contentDescription = screenState.currentWeather.name,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.FillHeight
            )
            Column(
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(top = 20.dp)
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = screenState.currentTemperature,
                    textAlign = TextAlign.Center,
                    fontSize = 40.sp
                )
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = screenState.currentWeather.name,
                    textAlign = TextAlign.Center,
                    fontSize = 40.sp
                )
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = screenState.cityName,
                    textAlign = TextAlign.Center,
                    fontSize = 14.sp
                )
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(
                    id = R.string.txt_min_temperature,
                    screenState.minimumTemperature
                ),
                textAlign = TextAlign.Center
            )
            Text(
                text = stringResource(
                    id = R.string.txt_current_temperature,
                    screenState.currentTemperature
                ),
                textAlign = TextAlign.Center
            )
            Text(
                text = stringResource(
                    id = R.string.txt_max_temperature,
                    screenState.maximumTemperature
                ),
                textAlign = TextAlign.Center
            )
        }
        Divider()
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            items(screenState.daysForecast) { forecast ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                ) {
                    Text(text = forecast.day, Modifier.weight(1f))
                    Image(
                        painter = painterResource(id = forecast.weather.forecastDrawable),
                        contentDescription = forecast.weather.name,
                        modifier = Modifier
                            .size(24.dp)
                            .weight(1f)
                    )
                    Text(
                        text = forecast.temperature,
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Right
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MainScreenPreview() {
    WeatherAppTheme {
        MainScreen()
    }
}
