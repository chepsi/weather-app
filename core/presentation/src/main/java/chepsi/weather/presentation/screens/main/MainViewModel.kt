package chepsi.weather.presentation.screens.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import chepsi.weather.presentation.R
import chepsi.weather.presentation.screens.main.model.DayForecast
import chepsi.weather.presentation.screens.main.model.MainScreenState
import chepsi.weather.presentation.screens.main.model.WeatherCondition.ForestCloudy
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {
    var mainScreenState by mutableStateOf(MainScreenState())

    fun onFetchMainScreenDetails() {
        viewModelScope.launch {
            val daysForecast = listOf(
                DayForecast("Monday", "Clear", "18", R.drawable.img_clear),
                DayForecast("Tuesday", "Cloudy", "8", R.drawable.img_partly_sunny),
                DayForecast("Wednesday", "Cloudy", "18", R.drawable.img_rainy),
                DayForecast("Thursday", "Cloudy", "18", R.drawable.img_clear),
                DayForecast("Friday", "Cloudy", "18", R.drawable.img_clear),
            )
            val newState = MainScreenState(
                currentTemperature = "18",
                minimumTemperature = "16",
                maximumTemperature = "23",
                currentWeather = ForestCloudy,
                daysForecast = daysForecast
            )

            mainScreenState = newState
        }
    }
}
