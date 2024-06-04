package com.example.radiotest.ui.view

import com.example.radiotest.domain.Station

sealed class UiState() {
    data object Loading : UiState()
    data class Success(val data: List<Station>) : UiState()
    data class Error(val error: String?) : UiState()

}