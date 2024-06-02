package com.example.radiotest.data

import com.example.radiotest.data.model.Available
import com.example.radiotest.data.model.RadioStation
import com.example.radiotest.data.service.RadioService
import io.reactivex.rxjava3.core.Single
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnit

class StationRepositoryTest {

    @Rule
    @JvmField
    val rule = MockitoJUnit.rule()!!

    @Rule
    @JvmField
    var testSchedulerRule = RxImmediateSchedulerRule()

    @Mock
    lateinit var radioService: RadioService

    lateinit var sut: StationRepository

    @Before
    fun setUp() {
        sut = StationRepositoryImpl(radioService)
    }

    @Test
    fun `should return list of stations when getting all stations`() {
        val stations = listOf(
            RadioStation("1", "1", "1"),
            RadioStation("2", "2", "2"),
            RadioStation("3", "3", "3")
        )

        Mockito.`when`(radioService.getAllRadioStations()).thenReturn(Single.just(stations))

        sut.getAllStations()
            .test()
            .assertNoErrors()
            .assertValue(stations)
            .assertValueCount(1)
    }

    @Test
    fun `should return error when getting all  stations`() {
        Mockito.`when`(radioService.getAllRadioStations()).thenReturn(Single.error(Exception()))

        sut.getAllStations()
            .test()
            .assertError(Exception::class.java)
            .assertNoValues()
    }

    @Test
    fun `should return empty list when getting all  stations`() {
        Mockito.`when`(radioService.getAllRadioStations()).thenReturn(Single.just(emptyList()))
        sut.getAllStations()
            .test()
            .assertNoErrors()
            .assertValue(emptyList())
            .assertValueCount(1)
    }

    @Test
    fun `should return list of available stations when getting stations availability`() {
        val availableStations = listOf(
            Available("ef3eb2ef-3011-45ba-b15d-109af983a5e5", "1"),
            Available("ef3eb2ef-3011-45ba-b15d-109af983a5e5", "2"),
            Available("ef3eb2ef-3011-45ba-b15d-109af983a5e5", "3")
        )

        Mockito.`when`(radioService.getCurrentStationAvailability("ef3eb2ef-3011-45ba-b15d-109af983a5e5"))
            .thenReturn(Single.just(availableStations))

        sut.getStationAvailability("ef3eb2ef-3011-45ba-b15d-109af983a5e5")
            .test()
            .assertNoErrors()
            .assertValue(availableStations)
    }

    @Test
    fun `should return error when getting stations availability`() {
        Mockito.`when`(radioService.getCurrentStationAvailability("ef3eb2ef-3011-45ba-b15d-109af983a5e5"))
            .thenReturn(Single.error(Exception()))

        sut.getStationAvailability("ef3eb2ef-3011-45ba-b15d-109af983a5e5")
            .test()
            .assertError(Exception::class.java)
            .assertNoValues()
    }
}