package com.example.radiotest.data

import app.cash.turbine.test
import com.example.radiotest.data.model.RadioStation
import com.example.radiotest.data.service.RadioService
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnit
import kotlin.test.assertEquals

class StationRepositoryTest {

    @Rule
    @JvmField
    val rule = MockitoJUnit.rule()!!

    @Mock
    lateinit var radioService: RadioService

    lateinit var sut: StationRepository

    @Before
    fun setUp() {
        sut = StationRepositoryImpl(radioService)
    }

    @Test
    fun `should return list of stations when getting all stations`() = runTest {

        Mockito.doReturn(STATION_LIST).`when`(radioService).getAllRadioStations()

        sut.getAllStations().test {
            assertEquals(STATION_LIST, awaitItem())
            awaitComplete()
        }
    }

    @Test
    fun `should return error when getting all stations`() = runTest {
        val exceptionMessage = "Broken"
        val exception = RuntimeException(exceptionMessage)
        Mockito.doThrow(exception).`when`(radioService).getAllRadioStations()

        sut.getAllStations().test {
            assertEquals(exceptionMessage, awaitError().message)
        }
    }

    @Test
    fun `should return empty list when getting all  stations`() = runTest {
        Mockito.doReturn(emptyList<RadioStation>()).`when`(radioService).getAllRadioStations()

        sut.getAllStations().test {
            assertEquals(emptyList(), awaitItem())
            awaitComplete()
        }

    }


    companion object {
        private val STATION_1 = RadioStation("1", "1", "1")
        private val STATION_2 = RadioStation("2", "2", "2")
        private val STATION_3 = RadioStation("3", "3", "3")
        private val STATION_LIST = listOf(
            STATION_1,
            STATION_2,
            STATION_3
        )
    }
}