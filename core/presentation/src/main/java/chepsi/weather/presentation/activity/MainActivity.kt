package chepsi.weather.presentation.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.res.stringResource
import chepsi.weather.presentation.R
import chepsi.weather.presentation.screens.error.ErrorScreen
import chepsi.weather.presentation.screens.main.MainScreen
import chepsi.weather.presentation.theme.WeatherAppTheme
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherAppTheme {
                val locationPermissions = rememberMultiplePermissionsState(
                    listOf(
                        android.Manifest.permission.ACCESS_FINE_LOCATION,
                        android.Manifest.permission.ACCESS_COARSE_LOCATION,
                    ),
                )
                if (locationPermissions.allPermissionsGranted) {
                    MainScreen()
                } else {
                    if (locationPermissions.shouldShowRationale) {
                        ErrorScreen(
                            errorMessage = stringResource(id = R.string.txt_error_location_rationale),
                            retryButtonText = stringResource(id = R.string.btn_retry),
                            onRetryAction = { locationPermissions.launchMultiplePermissionRequest() }
                        )
                    } else {
                        LaunchedEffect(key1 = Unit) {
                            locationPermissions.launchMultiplePermissionRequest()
                        }
                    }
                }
            }
        }
    }
}
