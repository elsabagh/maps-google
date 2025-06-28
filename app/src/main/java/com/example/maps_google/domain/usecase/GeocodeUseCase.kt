package com.example.maps_google.domain.usecase

import android.content.Context
import android.location.Geocoder
import com.example.maps_google.domain.model.LatLngPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.core.annotation.Factory

@Factory
class GeocodeUseCase(private val context: Context) {
    suspend operator fun invoke(address: String): LatLngPoint? {
        return withContext(Dispatchers.IO) {
            val geocoder = Geocoder(context)
            geocoder.getFromLocationName(address, 1)?.firstOrNull()?.let {
                LatLngPoint(it.latitude, it.longitude)
            }
        }
    }
}