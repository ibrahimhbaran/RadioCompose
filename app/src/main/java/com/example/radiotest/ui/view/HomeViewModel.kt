package com.example.radiotest.ui.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.radiotest.domain.GetRadioStationListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getRadioStationListUseCase: GetRadioStationListUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState = _uiState.asStateFlow()


    init {
        fetchRadioStationList()
    }

    private fun fetchRadioStationList() {
        viewModelScope.launch {
            getRadioStationListUseCase()
                .catch {
                    _uiState.value = UiState.Error(it.message)
                }
                .collect {
                    _uiState.value = UiState.Success(it)
                }
        }
    }

}