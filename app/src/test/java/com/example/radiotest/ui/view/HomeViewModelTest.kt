package com.example.radiotest.ui.view

import app.cash.turbine.test
import com.example.radiotest.data.MainDispatcherRule
import com.example.radiotest.domain.GetRadioStationListUseCase
import com.example.radiotest.domain.UiStation
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.doReturn
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnit
import kotlin.test.assertEquals
import kotlin.test.assertIs


class HomeViewModelTest {

    @Rule
    @JvmField
    val rule = MockitoJUnit.rule()!!


    lateinit var sut: HomeViewModel

    @Mock
    lateinit var getRadioStationListUseCase: GetRadioStationListUseCase


    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()


    @Test
    fun `when init home view model then home screen set UIState Success if return a list`() =
        runTest {
            Mockito.`when`(getRadioStationListUseCase()).thenReturn(flowOf(STATION_LIST))
            sut = HomeViewModel(getRadioStationListUseCase)
            assertIs<UiState.Success>(sut.uiState.value)
            sut.uiState.test {
                assertEquals(UiState.Success(STATION_LIST), awaitItem() )
                cancelAndIgnoreRemainingEvents()
            }
            verify(getRadioStationListUseCase).invoke()
        }

    @Test
    fun `when init home view model then home screen set UIState Error if there is error`() =
        runTest {
            val errorMessage = "Error Message For You"
            doReturn(flow<List<UiStation>> {
                throw IllegalStateException(errorMessage)
            }).`when`(getRadioStationListUseCase).invoke()
            sut = HomeViewModel(getRadioStationListUseCase)
            assertIs<UiState.Error>(sut.uiState.value)
            sut.uiState.test {
                assertEquals(UiState.Error(IllegalStateException(errorMessage).message), awaitItem())
                cancelAndIgnoreRemainingEvents()
            }
            verify(getRadioStationListUseCase).invoke()
        }

    companion object {
        val STATION_LIST = listOf(
            UiStation(
                stationName = "abc",
                url = "https:/something",
                country = "DE",
                favIcon = "",
                language = "DE"
            ),
            UiStation(
                stationName = "def",
                url = "https:/data",
                country = "UK",
                favIcon = "",
                language = "EN"
            )
        )
    }
}