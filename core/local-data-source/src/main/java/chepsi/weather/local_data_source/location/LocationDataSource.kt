package chepsi.weather.local_data_source.location

import android.annotation.SuppressLint
import android.os.Looper
import android.util.Log
import chepsi.weather.local_data_source.location.model.LocationDataSourceModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class LocationDataSource(
    private val fusedLocationClient: FusedLocationProviderClient,
    private val currentLocationRequest: LocationRequest,
) : LocationSource {

    @SuppressLint("MissingPermission")
    // Permission is checked in the Presentation Layer.
    override suspend fun fetchCurrentLocation(): Flow<LocationDataSourceModel> {
        return callbackFlow {
            val callback = object : LocationCallback() {
                override fun onLocationResult(result: LocationResult) {
                    try {
                        trySend(
                            LocationDataSourceModel(
                                longitude = result.lastLocation?.longitude ?: 0.0,
                                latitude = result.lastLocation?.latitude ?: 0.0
                            )
                        )
                    } catch (e: Exception) {
                        Log.e("Error", e.message.toString())
                    }
                }
            }
            fusedLocationClient.requestLocationUpdates(
                currentLocationRequest,
                callback,
                Looper.getMainLooper()
            )
                .addOnFailureListener { e ->
                    close(e)
                }

            awaitClose {
                fusedLocationClient.removeLocationUpdates(callback)
            }
        }
    }
}