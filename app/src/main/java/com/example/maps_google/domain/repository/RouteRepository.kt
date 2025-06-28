package com.example.maps_google.domain.repository

import com.example.maps_google.domain.model.LatLngPoint
import com.example.maps_google.presentation.map.Result

interface RouteRepository {
    suspend fun getRoute(origin: LatLngPoint, destination: LatLngPoint): Result<List<LatLngPoint>>
}