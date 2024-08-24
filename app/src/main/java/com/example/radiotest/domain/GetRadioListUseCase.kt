package com.example.radiotest.domain


import com.example.radiotest.data.StationRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetRadioStationListUseCase @Inject constructor(private val stationRepository: StationRepository) {

    operator fun invoke(): Flow<List<UiStation>> = stationRepository
        .getAllStations()
        .map { rs ->
            rs.map {
                UiStation(
                    stationName = it.name ?: "",
                    url = it.url ?: "",
                    country = it.country ?: "",
                    favIcon = it.favicon ?: "",
                    language = it.language ?: ""
                )
            }
        }
        .flowOn(Dispatchers.IO)


}