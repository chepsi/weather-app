package chepsi.weather.data.home.repository

import chepsi.weather.data.home.mappers.ApiToDataModelMapper.toData
import chepsi.weather.data.home.mappers.DataToDatabaseMapper.toCityEntity
import chepsi.weather.data.home.mappers.DataToDatabaseMapper.toForecastEntity
import chepsi.weather.data.home.mappers.DataToDatabaseMapper.toWeatherEntity
import chepsi.weather.data.home.mappers.DataToDomainModelMapper.toDomain
import chepsi.weather.data.home.mappers.DatabaseToDataModelMapper
import chepsi.weather.data.home.model.ForecastDataModel
import chepsi.weather.domain.home.model.HomeRepositoryDomainModel
import chepsi.weather.domain.home.repository.HomeRepository
import chepsi.weather.local_data_source.city.dao.CityDao
import chepsi.weather.local_data_source.location.LocationSource
import chepsi.weather.local_data_source.weather.dao.ForecastDao
import chepsi.weather.local_data_source.weather.dao.WeatherDao
import chepsi.weather.remote_data_source.api.WeatherRemoteSource
import chepsi.weather.remote_data_source.models.LocationRequestModel
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import timber.log.Timber

class HomeDataRepository @Inject constructor(
    private val weatherRemoteSource: WeatherRemoteSource,
    private val locationSource: LocationSource,
    private val weatherDao: WeatherDao,
    private val forecastDao: ForecastDao,
    private val cityDao: CityDao
) : HomeRepository {
    override suspend fun fetchHomeInformation(): Flow<HomeRepositoryDomainModel> = combine(
        flow = weatherDao.getAll(),
        flow2 = cityDao.getAll(),
        flow3 = forecastDao.getAll()
    ) { weather, city, forecast ->
         if (forecast.isEmpty()) {
            refreshDatabase().toDomain()
        } else {
            DatabaseToDataModelMapper.toData(weather, city, forecast).toDomain()
        }
    }.catch { exception ->
        Timber.e(exception)
    }

    private suspend fun refreshDatabase(): ForecastDataModel {
        val currentLocation = locationSource.fetchCurrentLocation().first()
        val locationRequestModel = LocationRequestModel(
            latitude = currentLocation.latitude,
            longitude = currentLocation.longitude
        )
        val weather = weatherRemoteSource.fetchCurrentLocationWeather(locationRequestModel)
        val forecast = weatherRemoteSource.fetchDaysAheadWeather(locationRequestModel)
        val data = weather.toData(forecast)
        weatherDao.insert(data.toWeatherEntity())
        forecastDao.insert(data.toForecastEntity())
        cityDao.insert(data.toCityEntity())
        return data
    }
}
