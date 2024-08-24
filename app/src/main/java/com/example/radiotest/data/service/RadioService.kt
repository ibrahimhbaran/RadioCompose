package com.example.radiotest.data.service

import com.example.radiotest.data.model.RadioStation
import retrofit2.http.GET

interface RadioService {

    @GET("stations/bylanguage/english")
    suspend fun getAllRadioStations(): List<RadioStation>

}