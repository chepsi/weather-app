package chepsi.weather.presentation.screens.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import chepsi.weather.presentation.screens.loading.WeatherAppLoadingComponent
import chepsi.weather.presentation.screens.main.components.MainScreenMainComponent
import chepsi.weather.presentation.theme.WeatherAppTheme

@Composable
fun MainScreen(mainScreenViewModel: MainViewModel = hiltViewModel()) {
    LaunchedEffect(key1 = Unit) {
        mainScreenViewModel.onFetchMainScreenDetails()
    }
    val screenState = mainScreenViewModel.mainScreenState
    when (screenState.isLoading) {
        true -> WeatherAppLoadingComponent()
        false -> MainScreenMainComponent(screenState = screenState)
    }
}

@Preview(showBackground = true)
@Composable
private fun MainScreenPreview() {
    WeatherAppTheme {
        MainScreen()
    }
}
