package com.example.radiotest.ui.view

import com.example.radiotest.domain.UiStation

sealed class UiState {
    data object Loading : UiState()
    data class Success(val data: List<UiStation>) : UiState()
    data class Error(val error: String?) : UiState()

}