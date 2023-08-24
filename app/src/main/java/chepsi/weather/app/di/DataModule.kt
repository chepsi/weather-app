package chepsi.weather.app.di

import chepsi.weather.data.home.repository.HomeDataRepository
import chepsi.weather.domain.home.repository.HomeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {
    @Binds
    @Singleton
    abstract fun provideHomeRepository(homeDataRepository: HomeDataRepository): HomeRepository
}
