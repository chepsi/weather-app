package chepsi.weather.data.home.repository

import chepsi.weather.domain.home.model.ForecastDomainModel
import chepsi.weather.domain.home.model.HomeRepositoryDomainModel
import chepsi.weather.domain.home.model.WeatherDomainModel.Sunny
import chepsi.weather.localdatasource.city.dao.CityDao
import chepsi.weather.localdatasource.city.model.CityEntity
import chepsi.weather.localdatasource.location.LocationSource
import chepsi.weather.localdatasource.weather.dao.ForecastDao
import chepsi.weather.localdatasource.weather.dao.WeatherDao
import chepsi.weather.localdatasource.weather.model.ForecastEntity
import chepsi.weather.localdatasource.weather.model.WeatherLocalSourceEntity
import chepsi.weather.remotedatasource.api.WeatherRemoteSource
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

private val givenWeatherEntity = WeatherLocalSourceEntity(
    cityId = 1,
    cityName = "Gotham",
    seaLevel = 1,
    weather = "Clear",
    minimumTemperature = 12.0,
    maximumTemperature = 20.0,
    temperature = 19.0,
    createdAt = 10L
)
private val givenCity = CityEntity(
    cityId = 1,
    cityName = "Gotham",
    isCurrent = true,
    latitude = 10.0,
    longitude = 10.0
)
private val givenForecast = ForecastEntity(
    id = 1,
    date = "2023-08-24 12:00:00",
    minimumTemperature = 10.0,
    maximumTemperature = 22.0,
    temperature = 20.0,
    weather = "Clear",
    cityId = 1
)

private val expectedValue = HomeRepositoryDomainModel(
    currentTemperature = 19,
    minimumTemperature = 12,
    maximumTemperature = 20,
    weather = Sunny,
    seaLevel = 1000,
    daysAheadForecast = listOf(
        ForecastDomainModel(day = "2023-08-24 12:00:00", weather = Sunny, temperature = 20)
    )
)

class HomeDataRepositoryTest {
    private lateinit var classUnderTest: HomeDataRepository

    private val weatherRemoteSource = mockk<WeatherRemoteSource>()
    private val locationSource = mockk<LocationSource>()
    private val weatherDao = mockk<WeatherDao>()
    private val forecastDao = mockk<ForecastDao>()
    private val cityDao = mockk<CityDao>()

    @BeforeEach
    fun setup() {
        classUnderTest = HomeDataRepository(
            weatherRemoteSource, locationSource, weatherDao, forecastDao, cityDao
        )
    }

    @AfterEach
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun `Given cached data When fetchHomeInformation() Then Returns DomainModel()`() = runBlocking {
        // Given
        coEvery { weatherDao.getAll() }.returns(flowOf(givenWeatherEntity))
        coEvery { cityDao.getAll() }.returns(flowOf(givenCity))
        coEvery { forecastDao.getAll() }.returns(flowOf(listOf(givenForecast)))

        // When
        var actualValue: HomeRepositoryDomainModel? = null
        classUnderTest.fetchHomeInformation().collect {
            actualValue = it
        }

        // Then
        assertEquals(expectedValue, actualValue)
    }

}