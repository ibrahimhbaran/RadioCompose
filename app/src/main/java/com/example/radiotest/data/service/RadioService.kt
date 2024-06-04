package com.example.radiotest.data.service

import com.example.radiotest.data.model.Available
import com.example.radiotest.data.model.RadioStation
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface RadioService {

    @GET("stations/bylanguage/english")
    fun getAllRadioStations(): Single<List<RadioStation>>

    @GET("checks/{stationuuid}")
    fun getCurrentStationAvailability(@Path("stationuuid") uuid: String?): Single<List<Available>>
}