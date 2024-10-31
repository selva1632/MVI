package com.selva.mvi.data

import kotlinx.coroutines.delay

class Repository {
    suspend fun sendMessage(text: String) {
        // Do networking or local data storage alterations here
        // this delay is just a mock
        delay(3000L)
    }
}