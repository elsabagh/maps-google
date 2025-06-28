package com.example.maps_google.presentation.map

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.maps_google.presentation.components.AddressInputDialog
import com.example.maps_google.presentation.components.RouteButton
import com.example.maps_google.presentation.components.RouteMap
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun MapScreen(viewModel: MapViewModel) {
    val state by viewModel.state.collectAsState()
    val context = LocalContext.current

    var showDialog by remember { mutableStateOf(false) }
    var originText by remember { mutableStateOf("") }
    var destinationText by remember { mutableStateOf("") }

    val cameraPositionState = rememberCameraPositionState()

    Box(modifier = Modifier.fillMaxSize()) {
        RouteMap(
            state = state,
            cameraPositionState = cameraPositionState
        )

        RouteButton(onClick = { showDialog = true })

        if (showDialog) {
            AddressInputDialog(
                originText = originText,
                destinationText = destinationText,
                onOriginChange = { originText = it },
                onDestinationChange = { destinationText = it },
                onConfirm = {
                    if (originText.isBlank() || destinationText.isBlank()) {
                        Toast.makeText(context, "الرجاء إدخال العنوانين", Toast.LENGTH_SHORT).show()
                    } else {
                        showDialog = false
                        viewModel.drawRouteByAddress(originText, destinationText)
                    }
                },
                onDismiss = { showDialog = false }
            )
        }
    }
}