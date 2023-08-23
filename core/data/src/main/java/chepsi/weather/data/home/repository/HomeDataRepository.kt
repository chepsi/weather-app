package chepsi.weather.data.home.repository

import chepsi.weather.data.home.mappers.ApiToDataModelMapper.toData
import chepsi.weather.data.home.mappers.DataToDomainModelMapper.toDomain
import chepsi.weather.data.home.model.ForecastDataModel
import chepsi.weather.domain.home.model.HomeRepositoryDomainModel
import chepsi.weather.domain.home.repository.HomeRepository
import chepsi.weather.local_data_source.city.dao.CityDao
import chepsi.weather.local_data_source.city.model.CityEntity
import chepsi.weather.local_data_source.location.LocationSource
import chepsi.weather.local_data_source.weather.dao.ForecastDao
import chepsi.weather.local_data_source.weather.dao.WeatherDao
import chepsi.weather.local_data_source.weather.model.ForecastEntity
import chepsi.weather.local_data_source.weather.model.WeatherLocalSourceEntity
import chepsi.weather.remote_data_source.api.WeatherRemoteSource
import chepsi.weather.remote_data_source.models.LocationRequestModel
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf

class HomeDataRepository @Inject constructor(
    private val weatherRemoteSource: WeatherRemoteSource,
    private val locationSource: LocationSource,
    private val weatherDao: WeatherDao,
    private val forecastDao: ForecastDao,
    private val cityDao: CityDao
) : HomeRepository {
    override suspend fun fetchHomeInformation(): Flow<HomeRepositoryDomainModel> {
        val currentLocation = locationSource.fetchCurrentLocation().first()
        val locationRequestModel = LocationRequestModel(
            latitude = currentLocation.latitude,
            longitude = currentLocation.longitude
        )
        return combine(
            flow = flowOf(weatherRemoteSource.fetchCurrentLocationWeather(locationRequestModel)),
            flow2 = flowOf(weatherRemoteSource.fetchDaysAheadWeather(locationRequestModel))
        ) { currentWeatherApiModel, forecastApiModel ->
            val dataModel = currentWeatherApiModel.toData(forecastApiModel)
            refreshDatabase(dataModel)
            dataModel.toDomain()
        }
    }

    private suspend fun refreshDatabase(dataModel: ForecastDataModel){
        val weatherEntity = WeatherLocalSourceEntity(
            cityId = dataModel.cityId,
            cityName = dataModel.cityName,
            seaLevel = dataModel.seaLevel,
            createdAt = System.currentTimeMillis(),
            maximumTemperature = dataModel.maximumTemperature,
            minimumTemperature = dataModel.minimumTemperature,
            temperature = dataModel.currentTemperature,
            weather = dataModel.weather
        )
        val cityEntity = CityEntity(
            cityId = dataModel.cityId,
            cityName = dataModel.cityName,
            isCurrent = true,
            latitude = dataModel.coordinates.latitude,
            longitude = dataModel.coordinates.longitude
        )
        val forecastEntity = dataModel.cityForecast.map {
            ForecastEntity(
                minimumTemperature = it.minimumTemperature,
                maximumTemperature = it.maximumTemperature,
                temperature = it.temperature,
                date = it.date,
                weather = it.weather,
                cityId = dataModel.cityId
            )
        }

        cityDao.insert(cityEntity)
        forecastDao.insert(forecastEntity)
        weatherDao.insert(weatherEntity)
    }
}
