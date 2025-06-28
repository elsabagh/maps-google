package com.example.maps_google.presentation.map

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.maps_google.domain.model.LatLngPoint
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Polyline
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun MapScreen(viewModel: MapViewModel) {
    val state by viewModel.state.collectAsState()
    val origin = LatLngPoint(30.0444, 31.2357)
    val destination = LatLngPoint(29.9765, 31.1313)

    Box(Modifier.fillMaxSize()) {
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = rememberCameraPositionState {
                position = CameraPosition.fromLatLngZoom(LatLng(origin.lat, origin.lng), 10f)
            }
        ) {
            if (state is MapUiState.Success) {
                Polyline(
                    points = (state as MapUiState.Success).route,
                    color = Color.Blue,
                    width = 8f
                )
            }
        }

        Button(
            onClick = { viewModel.drawRoute(origin, destination) },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(16.dp)
        ) {
            Text("عرض المسار")
        }
    }
}