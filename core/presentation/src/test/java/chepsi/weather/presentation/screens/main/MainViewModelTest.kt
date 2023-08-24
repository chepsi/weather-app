package chepsi.weather.presentation.screens.main

import chepsi.weather.domain.home.model.ForecastDomainModel
import chepsi.weather.domain.home.model.HomeRepositoryDomainModel
import chepsi.weather.domain.home.model.WeatherDomainModel.Sunny
import chepsi.weather.domain.home.repository.HomeRepository
import chepsi.weather.presentation.screens.main.model.DayForecast
import chepsi.weather.presentation.screens.main.model.MainScreenState
import chepsi.weather.presentation.screens.main.model.WeatherPresentationModel.ForestSunny
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class MainViewModelTest {
    private lateinit var classUnderTest: MainViewModel

    private val homeRepository = mockk<HomeRepository>()

    @BeforeEach
    fun setup() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
        classUnderTest = MainViewModel(homeRepository)
    }

    @AfterEach
    fun tearDown() {
        Dispatchers.resetMain()
        clearAllMocks()
    }

    @Test
    fun `Given correct response When onFetchMainScreenDetails Then updates the screenstate`() {
        // Given
        val givenForecast = listOf(
            ForecastDomainModel(
                day = "2023-08-24 18:00:00",
                weather = Sunny,
                temperature = 20
            )
        )
        val givenResponse = HomeRepositoryDomainModel(
            currentTemperature = 20,
            minimumTemperature = 18,
            maximumTemperature = 22,
            weather = Sunny,
            seaLevel = 1000,
            daysAheadForecast = givenForecast,
            cityName = "Gotham"
        )
        coEvery { homeRepository.fetchHomeInformation() } returns flowOf(givenResponse)

        // When
        classUnderTest.onFetchMainScreenDetails()

        // Then
        val expectedValue = MainScreenState(
            currentTemperature = "20 °",
            minimumTemperature = "18 °",
            maximumTemperature = "22 °",
            currentWeather = ForestSunny,
            daysForecast = listOf(
                DayForecast(
                    day = "Thursday",
                    temperature = "20 °",
                    weather = ForestSunny
                )
            ),
            cityName = "Gotham"
        )
        val actualValue = classUnderTest.mainScreenState
        assertEquals(expectedValue, actualValue)
    }
}
