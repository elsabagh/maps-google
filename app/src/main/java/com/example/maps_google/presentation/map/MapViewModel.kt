package com.example.maps_google.presentation.map

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.maps_google.domain.model.LatLngPoint
import com.example.maps_google.domain.usecase.GetRouteUseCase
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class MapViewModel(
    private val getRouteUseCase: GetRouteUseCase,
) : ViewModel() {
    private val _state = MutableStateFlow<MapUiState>(MapUiState.Idle)
    val state: StateFlow<MapUiState> = _state.asStateFlow()

    fun drawRoute(start: LatLngPoint, end: LatLngPoint) {
        viewModelScope.launch {
            _state.value = MapUiState.Loading
            when (val result = getRouteUseCase(start, end)) {
                is Result.Success -> _state.value =
                    MapUiState.Success(result.data.map { LatLng(it.lat, it.lng) })

                is Result.Error -> _state.value = MapUiState.Error(result.message)
                else -> {
                    _state.value = MapUiState.Error("Unknown error occurred")
                }
            }
        }
    }

}