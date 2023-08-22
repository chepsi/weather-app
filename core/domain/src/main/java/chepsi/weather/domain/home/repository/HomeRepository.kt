package chepsi.weather.domain.home.repository

import chepsi.weather.domain.home.model.HomeRepositoryDomainModel
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
    suspend fun fetchHomeInformation(): Flow<HomeRepositoryDomainModel>
}
