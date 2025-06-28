package com.example.maps_google.data.repository

import com.example.maps_google.BuildConfig.MAPS_API_KEY
import com.example.maps_google.data.api.GoogleApi
import com.example.maps_google.domain.model.LatLngPoint
import com.example.maps_google.domain.repository.RouteRepository
import com.example.maps_google.presentation.map.Result
import com.google.maps.android.PolyUtil
import org.koin.core.annotation.Single

@Single
class RouteRepositoryImpl(private val api: GoogleApi) : RouteRepository {

    override suspend fun getRoute(
        origin: LatLngPoint,
        destination: LatLngPoint,
    ): Result<List<LatLngPoint>> {

        return try {
            val response = api.getDirections(
                "${origin.lat},${origin.lng}",
                "${destination.lat},${destination.lng}",
                MAPS_API_KEY
            )
            val encoded = response.routes.firstOrNull()?.overview_polyline?.points.orEmpty()
            val points = PolyUtil.decode(encoded).map { LatLngPoint(it.latitude, it.longitude) }
            if (points.isEmpty()) Result.Error("Empty route") else Result.Success(points)
        } catch (e: Exception) {
            Result.Error(e.message ?: "Unknown error")
        }
    }
}
