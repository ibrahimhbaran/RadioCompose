package com.example.radiotest.data

import com.example.radiotest.data.model.Available
import com.example.radiotest.data.model.RadioStation
import io.reactivex.rxjava3.core.Single

interface StationRepository {

    fun getAllStations(): Single<List<RadioStation>>

    fun getStationAvailability(stationuuid: String): Single<List<Available>>

}