package chepsi.weather.presentation.screens.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import chepsi.weather.domain.home.model.WeatherDomainModel.Cloudy
import chepsi.weather.domain.home.model.WeatherDomainModel.Default
import chepsi.weather.domain.home.model.WeatherDomainModel.Rainy
import chepsi.weather.domain.home.model.WeatherDomainModel.Sunny
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
                        Cloudy -> ForestCloudy
                        Sunny, Default -> WeatherCondition.ForestSunny
                        Rainy -> WeatherCondition.ForestRainy
                    },
                    daysForecast = homeInformation.daysAheadForecast.map { forecast ->
                        DayForecast(
                            day = forecast.day.toDayOfWeek(),
                            name = forecast.weather.name,
                            temperature = forecast.temperature.toString(),
                            icon = when (forecast.weather) {
                                Cloudy -> R.drawable.img_partly_sunny
                                Sunny -> R.drawable.img_clear
                                Rainy -> R.drawable.img_rainy
                                Default -> R.drawable.img_rainy
                            }
                        )
                    }
                )
            }
        }
    }
}
