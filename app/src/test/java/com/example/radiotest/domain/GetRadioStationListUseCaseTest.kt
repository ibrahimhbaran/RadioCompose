package com.example.radiotest.domain

import com.example.radiotest.data.StationRepository
import com.example.radiotest.data.model.Available
import com.example.radiotest.data.model.RadioStation
import io.reactivex.rxjava3.core.Single
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.any
import org.mockito.Mockito.mock


class GetRadioStationListUseCaseTest {

    @Test
    fun `when invoke getRadioStationListUseCase then return transformed radio station list `() {
        val repository = mock(StationRepository::class.java)
        Mockito.`when`(repository.getStationAvailability(any())).thenReturn(Single.just(AVAILABLE_LIST))
        Mockito.`when`(repository.getAllStations()).thenReturn(Single.just(STATION_LIST))
        val useCase = GetRadioStationListUseCase(repository)
        useCase.invoke()
            .test()
            .assertNoErrors()
            .assertValue(EXPECTED_RESULT)
            .assertValueCount(1)

    }

    companion object{
       val STATION_LIST = listOf(RadioStation("abc","1"), RadioStation("def","2"))
       val AVAILABLE_LIST = listOf(Available("1","asdf"), Available("2","asdlf"))
       val EXPECTED_RESULT = listOf(Station(STATION_LIST[0],AVAILABLE_LIST[0]),Station(STATION_LIST[1],AVAILABLE_LIST[0]))
    }

}