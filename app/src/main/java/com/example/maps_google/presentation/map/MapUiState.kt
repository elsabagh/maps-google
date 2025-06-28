package com.example.maps_google.presentation.map

import com.example.maps_google.domain.model.LatLngPoint
import com.google.android.gms.maps.model.LatLng

sealed class MapUiState {
    object Idle : MapUiState()
    object Loading : MapUiState()
    data class Success(
        val route: List<LatLng>,
        val origin: LatLngPoint,
        val destination: LatLngPoint,
    ) : MapUiState()

    data class Error(val message: String) : MapUiState()
}