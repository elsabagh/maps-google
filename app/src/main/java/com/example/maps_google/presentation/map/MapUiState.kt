package com.example.maps_google.presentation.map

import com.google.android.gms.maps.model.LatLng

sealed class MapUiState {
    object Idle : MapUiState()
    object Loading : MapUiState()
    data class Success(val route: List<LatLng>) : MapUiState()
    data class Error(val message: String) : MapUiState()
}
