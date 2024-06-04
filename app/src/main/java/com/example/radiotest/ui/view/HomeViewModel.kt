package com.example.radiotest.ui.view

import androidx.lifecycle.ViewModel
import com.example.radiotest.domain.GetRadioStationListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getRadioStationListUseCase: GetRadioStationListUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState = _uiState.asStateFlow()

    private val compositeDisposable = CompositeDisposable()

    init {
        fetchRadioStationList()
    }

    private fun fetchRadioStationList() {
        compositeDisposable.add(
            getRadioStationListUseCase()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        _uiState.value = UiState.Success(it)
                    },
                    {
                        _uiState.value = UiState.Error(it.message)
                    }
                )
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

}