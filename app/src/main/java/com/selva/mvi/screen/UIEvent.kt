package com.selva.mvi.screen

sealed class UIEvent {
    data class SendMessage(val message: String) : UIEvent()
    data class UpdateText(val message: String) : UIEvent()
    data object MessageShown : UIEvent()
}