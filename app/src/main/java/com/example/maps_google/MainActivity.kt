package com.example.maps_google

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.maps_google.presentation.map.MapScreen
import com.example.maps_google.presentation.map.MapViewModel
import com.example.maps_google.ui.theme.MapsgoogleTheme
import org.koin.android.annotation.KoinViewModel
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MapsgoogleTheme {
                setContent {
                    val viewModel: MapViewModel = koinViewModel()
                    MapScreen(viewModel)
                }
            }
        }
    }
}
