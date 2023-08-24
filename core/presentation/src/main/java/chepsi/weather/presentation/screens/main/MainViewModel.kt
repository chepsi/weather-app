package chepsi.weather.presentation.screens.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import chepsi.weather.domain.home.repository.HomeRepository
import chepsi.weather.presentation.screens.main.mapper.DomainToPresentationMappers.toPresentation
import chepsi.weather.presentation.screens.main.mapper.DomainToPresentationMappers.toTemperatureString
import chepsi.weather.presentation.screens.main.model.MainScreenState
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
                    currentTemperature = homeInformation.currentTemperature.toTemperatureString(),
                    minimumTemperature = homeInformation.minimumTemperature.toTemperatureString(),
                    maximumTemperature = homeInformation.maximumTemperature.toTemperatureString(),
                    currentWeather = homeInformation.weather.toPresentation(),
                    daysForecast = homeInformation.daysAheadForecast.toPresentation(),
                    cityName = homeInformation.cityName
                )
            }
        }
    }
}
