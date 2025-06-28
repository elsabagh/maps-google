package com.example.maps_google.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.maps_google.ui.theme.MapsgoogleTheme

@Composable
fun AddressInputDialog(
    originText: String,
    destinationText: String,
    onOriginChange: (String) -> Unit,
    onDestinationChange: (String) -> Unit,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit,
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = onConfirm) {
                Text("OK")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("cancel")
            }
        },
        title = { Text("Enter address") },
        text = {
            Column {
                OutlinedTextField(
                    value = originText,
                    onValueChange = onOriginChange,
                    label = { Text("Current location") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = destinationText,
                    onValueChange = onDestinationChange,
                    label = { Text("Destination") },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    )
}

@Preview(
    showBackground = true,
)
@Composable
fun AddressInputDialogPreview() {
    MapsgoogleTheme {
        AddressInputDialog(
            originText = "Current location",
            destinationText = "Destination",
            onOriginChange = {},
            onDestinationChange = {},
            onConfirm = {},
            onDismiss = {}
        )
    }
}