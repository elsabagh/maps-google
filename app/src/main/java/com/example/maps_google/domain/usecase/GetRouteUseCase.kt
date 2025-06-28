package com.example.maps_google.domain.usecase

import com.example.maps_google.domain.model.LatLngPoint
import com.example.maps_google.domain.repository.RouteRepository
import com.example.maps_google.presentation.map.Result
import org.koin.core.annotation.Factory

@Factory
class GetRouteUseCase(private val repository: RouteRepository) {
    suspend operator fun invoke(origin: LatLngPoint, destination: LatLngPoint): Result<List<LatLngPoint>> {
        return repository.getRoute(origin, destination)
    }
}
