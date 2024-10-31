package com.selva.mvi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.selva.mvi.screen.MessageScreen
import com.selva.mvi.screen.MviViewModel
import com.selva.mvi.ui.theme.MVITheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MviViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MVITheme {
                val state by viewModel.state.collectAsStateWithLifecycle()
                MessageScreen(
                    state,
                    sendEvent = viewModel::handleIntent
                )
            }
        }
    }
}