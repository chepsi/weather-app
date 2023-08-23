package chepsi.weather.presentation.screens.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import chepsi.weather.domain.home.model.WeatherDomainModel
import chepsi.weather.domain.home.repository.HomeRepository
import chepsi.weather.presentation.R
import chepsi.weather.presentation.screens.main.mapper.DateMapper.toDayOfWeek
import chepsi.weather.presentation.screens.main.model.DayForecast
import chepsi.weather.presentation.screens.main.model.MainScreenState
import chepsi.weather.presentation.screens.main.model.WeatherCondition
import chepsi.weather.presentation.screens.main.model.WeatherCondition.ForestCloudy
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class MainViewModel @Inject constructor(
    private val homeRepository: HomeRepository
) : ViewModel() {
    var mainScreenState by mutableStateOf(MainScreenState())

    fun onFetchMainScreenDetails() {
        viewModelScope.launch {
            homeRepository.fetchHomeInformation().collect { homeInformation ->
                mainScreenState = mainScreenState.copy(
                    currentTemperature = homeInformation.currentTemperature.toString(),
                    minimumTemperature = homeInformation.minimumTemperature.toString(),
                    maximumTemperature = homeInformation.maximumTemperature.toString(),
                    currentWeather = when (homeInformation.weather) {
                        WeatherDomainModel.Cloudy -> ForestCloudy
                        WeatherDomainModel.Sunny, WeatherDomainModel.Default -> WeatherCondition.ForestSunny
                        WeatherDomainModel.Rainy -> WeatherCondition.ForestRainy
                    },
                    daysForecast = homeInformation.daysAheadForecast.map { forecast ->
                        DayForecast(
                            day = forecast.day.toDayOfWeek(),
                            name = forecast.weather.name,
                            temperature = forecast.temperature.toString(),
                            icon = when (forecast.weather) {
                                WeatherDomainModel.Cloudy -> R.drawable.img_partly_sunny
                                WeatherDomainModel.Sunny -> R.drawable.img_clear
                                WeatherDomainModel.Rainy -> R.drawable.img_rainy
                                WeatherDomainModel.Default -> R.drawable.img_rainy
                            }
                        )
                    }
                )
            }
        }
    }
}
