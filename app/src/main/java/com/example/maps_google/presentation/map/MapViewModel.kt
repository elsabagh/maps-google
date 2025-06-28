package com.example.maps_google.presentation.map

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.maps_google.domain.model.LatLngPoint
import com.example.maps_google.domain.usecase.GeocodeUseCase
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
    private val geocodeUseCase: GeocodeUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow<MapUiState>(MapUiState.Idle)
    val state: StateFlow<MapUiState> = _state.asStateFlow()

    private fun drawRoute(start: LatLngPoint, end: LatLngPoint) {
        viewModelScope.launch {
            _state.value = MapUiState.Loading
            when (val result = getRouteUseCase(start, end)) {
                is Result.Success -> {
                    val points = result.data.map { LatLng(it.lat, it.lng) }
                    _state.value = MapUiState.Success(points, start, end)
                }
                is Result.Error -> _state.value = MapUiState.Error(result.message)
                else -> _state.value = MapUiState.Error("Unknown error")
            }
        }
    }

    fun drawRouteByAddress(originText: String, destinationText: String) {
        viewModelScope.launch {
            if (originText.isBlank() || destinationText.isBlank()) {
                _state.value = MapUiState.Error("الرجاء إدخال العنوانين")
                return@launch
            }

            val origin = geocodeUseCase(originText)
            val destination = geocodeUseCase(destinationText)

            if (origin != null && destination != null) {
                drawRoute(origin, destination)
            } else {
                _state.value = MapUiState.Error("تعذر تحديد العناوين")
            }
        }
    }
}
