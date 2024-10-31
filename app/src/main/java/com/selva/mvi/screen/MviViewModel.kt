package com.selva.mvi.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.selva.mvi.data.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MviViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _state = MutableStateFlow(UIState())
    val state = _state.asStateFlow()

    fun handleIntent(event: UIEvent) {
        when (event) {
            is UIEvent.SendMessage -> sendMessage()
            is UIEvent.UpdateText -> updateMessage(event.message)
            is UIEvent.MessageShown -> messageShown()
        }
    }

    private fun sendMessage() {
        _state.update { state -> state.copy(isSending = true) }
        viewModelScope.launch {
            repository.sendMessage("")
            _state.update { state ->
                state.copy(
                    isSending = false,
                    text = "",
                    result = "Message sent successfully"
                )
            }
        }
    }

    private fun updateMessage(message: String) {
        _state.update { state -> state.copy(text = message) }
    }

    private fun messageShown() {
        _state.update { state -> state.copy(result = null) }
    }
}