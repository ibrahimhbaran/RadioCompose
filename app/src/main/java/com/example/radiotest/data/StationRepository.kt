package com.example.radiotest.data

import com.example.radiotest.data.model.RadioStation
import kotlinx.coroutines.flow.Flow

interface StationRepository {

    fun getAllStations(): Flow<List<RadioStation>>

}