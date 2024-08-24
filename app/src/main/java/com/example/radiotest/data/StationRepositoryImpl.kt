package com.example.radiotest.data

import com.example.radiotest.data.service.RadioService
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class StationRepositoryImpl @Inject constructor(
    private val radioService: RadioService
) : StationRepository {
    override fun getAllStations() = flow {
        emit(radioService.getAllRadioStations())
    }

}