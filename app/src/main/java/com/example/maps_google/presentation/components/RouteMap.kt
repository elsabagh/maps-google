package com.example.maps_google.presentation.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.example.maps_google.R
import com.example.maps_google.presentation.map.MapUiState
import com.example.utils.vectorToBitmap
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.Polyline

@Composable
fun RouteMap(state: MapUiState, cameraPositionState: CameraPositionState) {
    val context = LocalContext.current

    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState
    ) {
        if (state is MapUiState.Success) {
            Polyline(
                points = state.route,
                color = Color.Blue,
                width = 8f
            )

            val originMarkerState =
                remember { MarkerState(position = LatLng(state.origin.lat, state.origin.lng)) }
            val destinationMarkerState = remember {
                MarkerState(
                    position = LatLng(
                        state.destination.lat,
                        state.destination.lng
                    )
                )
            }

            val personIcon = remember {
                vectorToBitmap(context, R.drawable.baseline_circle_24)
            }

            Marker(
                state = originMarkerState,
                icon = personIcon,
                title = "موقعي"
            )

            Marker(
                state = destinationMarkerState,
                title = "الوجهة",
                icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)
            )

            LaunchedEffect(state.origin) {
                cameraPositionState.move(
                    CameraUpdateFactory.newLatLngZoom(
                        LatLng(state.origin.lat, state.origin.lng),
                        10f
                    )
                )
            }
        }
    }
}