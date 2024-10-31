package com.selva.mvi.screen

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun MessageScreen(
    state: UIState,
    sendEvent: (UIEvent) -> Unit
) {
    val context = LocalContext.current

    LaunchedEffect(state.result) {
        if (state.result != null) {
            Toast.makeText(context, state.result, Toast.LENGTH_SHORT).show()
            sendEvent(UIEvent.MessageShown)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (state.isSending) {
            CircularProgressIndicator()
        } else {
            OutlinedTextField(
                value = state.text,
                onValueChange = {
                    sendEvent(UIEvent.UpdateText(it))
                },
                label = { Text(text = "Message") }
            )
            Button(
                onClick = { sendEvent(UIEvent.SendMessage(state.text)) },
                enabled = state.text.isNotEmpty()
            ) {
                Text(text = "Send")
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun PreviewMessageScreen() {
    MessageScreen(state = UIState()) {}
}