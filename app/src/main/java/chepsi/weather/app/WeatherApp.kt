package chepsi.weather.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class WeatherApp : Application() {

    override fun onCreate() {
        super.onCreate()
        plantDebugBuildLogger()
    }

    private fun plantDebugBuildLogger() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}
