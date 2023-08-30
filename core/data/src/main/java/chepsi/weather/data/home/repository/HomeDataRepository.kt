package chepsi.weather.data.home.repository

import chepsi.weather.data.home.mappers.ApiToDataModelMapper.toData
import chepsi.weather.data.home.mappers.DataToDatabaseModelMapper.toCityEntity
import chepsi.weather.data.home.mappers.DataToDatabaseModelMapper.toForecastEntity
import chepsi.weather.data.home.mappers.DataToDatabaseModelMapper.toWeatherEntity
import chepsi.weather.data.home.mappers.DataToDomainModelMapper
import chepsi.weather.data.home.mappers.DatabaseToDataModelMapper
import chepsi.weather.data.home.model.ForecastDataModel
import chepsi.weather.data.home.utils.DateAndTimeUtils
import chepsi.weather.domain.home.model.HomeRepositoryDomainModel
import chepsi.weather.domain.home.repository.HomeRepository
import chepsi.weather.localdatasource.city.dao.CityDao
import chepsi.weather.localdatasource.location.LocationSource
import chepsi.weather.localdatasource.weather.dao.ForecastDao
import chepsi.weather.localdatasource.weather.dao.WeatherDao
import chepsi.weather.remotedatasource.api.WeatherRemoteSource
import chepsi.weather.remotedatasource.models.LocationRequestModel
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
    private val cityDao: CityDao,
    private val dateAndTimeUtils: DateAndTimeUtils,
    private val dataToDomainModelMapper: DataToDomainModelMapper
) : HomeRepository {
    override suspend fun fetchHomeInformation(): Flow<HomeRepositoryDomainModel> = combine(
        flow = weatherDao.getAll(),
        flow2 = cityDao.getAll(),
        flow3 = forecastDao.getAll()
    ) { weather, city, forecast ->
        if (forecast.isEmpty() || weather.createdAt.shouldSync()) {
            dataToDomainModelMapper.toDomain(refreshDatabase())
        } else {
            val forecastModel = DatabaseToDataModelMapper.toData(weather, city, forecast)
            dataToDomainModelMapper.toDomain(forecastModel)
        }
    }.catch { exception ->
        Timber.e(exception)
    }

    private fun Long.shouldSync(): Boolean {
        val today = dateAndTimeUtils.todayInDate()
        val syncData = dateAndTimeUtils.convertToDate(this)
        return syncData != today
    }

    private suspend fun refreshDatabase(): ForecastDataModel {
        val currentLocation = locationSource.fetchCurrentLocation().first()
        val locationRequestModel = LocationRequestModel(
            latitude = currentLocation.latitude,
            longitude = currentLocation.longitude
        )
        val weather = weatherRemoteSource.fetchWeather(locationRequestModel)
        val forecast = weatherRemoteSource.fetchForecast(locationRequestModel)
        val data = weather.toData(forecast)
        val currentTime = System.currentTimeMillis()
        weatherDao.insert(data.toWeatherEntity(currentTime))
        forecastDao.insert(data.toForecastEntity())
        cityDao.insert(data.toCityEntity())
        return data
    }
}
