package com.example.radiotest.ui.view

import com.example.radiotest.data.RxImmediateSchedulerRule
import com.example.radiotest.data.model.Available
import com.example.radiotest.data.model.RadioStation
import com.example.radiotest.domain.GetRadioStationListUseCase
import com.example.radiotest.domain.Station
import io.reactivex.rxjava3.core.Single
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnit
import kotlin.test.assertEquals
import kotlin.test.assertIs


class HomeViewModelTest{

    @Rule
    @JvmField
    val rule = MockitoJUnit.rule()!!

    @Rule
    @JvmField
    var testSchedulerRule = RxImmediateSchedulerRule()

    lateinit var sut: HomeViewModel

    @Mock
    lateinit var getHomeUseCase: GetRadioStationListUseCase


    @Test
    fun `when init home view model then home screen set UIState Success if return a list`(){
        Mockito.`when`(getHomeUseCase()).thenReturn(Single.just(STATION_LIST))
        sut = HomeViewModel(getHomeUseCase)
        assertIs<UiState.Success>( sut.uiState.value)
        assertEquals(STATION_LIST, (sut.uiState.value as UiState.Success).data )

    }

    @Test
    fun `when init home view model then home screen set UIState Error if there is error`(){
        Mockito.`when`(getHomeUseCase()).thenReturn(Single.error(Throwable()))
        sut = HomeViewModel(getHomeUseCase)
        assertIs<UiState.Error>( sut.uiState.value)
    }

    companion object{
        val STATION_LIST = listOf(
            Station(RadioStation("1a"), Available("a")),
            Station(RadioStation("1b"), Available("b")),
            Station(RadioStation("1c"), Available("c")),
            Station(RadioStation("1d"), Available("d")),
            )
    }

}