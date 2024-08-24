package com.example.radiotest.domain

import app.cash.turbine.test
import com.example.radiotest.data.StationRepository
import com.example.radiotest.data.model.RadioStation
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock
import kotlin.test.assertEquals


class GetRadioUiStationListUseCaseTest {

    @Test
    fun `when invoke getRadioStationListUseCase then return transformed radio station list`() =
        runTest {
            val repository = mock(StationRepository::class.java)
            Mockito.doReturn(flowOf(STATION_LIST)).`when`(repository).getAllStations()
            val useCase = GetRadioStationListUseCase(repository)
            useCase.invoke().test {
                assertEquals(EXPECTED_LIST, awaitItem())
                cancelAndIgnoreRemainingEvents()
            }
        }

    companion object {
        val STATION_LIST =
            listOf(
                RadioStation(name = "abc", url = "https:/something"),
                RadioStation(name = "def", url = "tata", country = "UK")
            )
        val EXPECTED_LIST = listOf(
            UiStation(
                stationName = "abc",
                url = "https:/something",
                country = "",
                favIcon = "",
                language = ""
            ),
            UiStation(
                stationName = "def",
                url = "tata",
                country = "UK",
                favIcon = "",
                language = ""
            )
        )
    }

}