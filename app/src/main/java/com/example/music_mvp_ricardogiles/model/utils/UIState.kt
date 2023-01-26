package com.example.music_mvp_ricardogiles.model.utils

import com.example.music_mvp_ricardogiles.model.GeneralResponse


sealed class UIState {
    object LOADING : UIState()
    data class SUCCESS(val response: GeneralResponse) : UIState()
    class ERROR(val e: Exception) : UIState()
}
