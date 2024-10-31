package com.selva.mvi.screen

data class UIState(
    val isSending: Boolean = false,
    val text: String = "",
    val result: String? = null
)
