package chepsi.weather.app.di

import chepsi.weather.app.BuildConfig
import chepsi.weather.remotedatasource.api.WeatherRemoteSource
import chepsi.weather.remotedatasource.api.WeatherRemoteSourceImpl
import chepsi.weather.remotedatasource.utils.HttpClientFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.android.AndroidClientEngine
import io.ktor.client.engine.android.AndroidEngineConfig
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideHttpClientEngine(): HttpClientEngine = AndroidClientEngine(AndroidEngineConfig())

    @Provides
    @Singleton
    fun provideHttpClient(engine: HttpClientEngine): HttpClient = HttpClientFactory().create(engine)

    @Provides
    fun providesWeatherRemoteSource(
        httpClient: HttpClient,
        @Named("API_KEY") apiKey: String
    ): WeatherRemoteSource = WeatherRemoteSourceImpl(httpClient, apiKey)

    @Provides
    @Named("API_KEY")
    fun providesApiKey() = BuildConfig.API_KEY
}
