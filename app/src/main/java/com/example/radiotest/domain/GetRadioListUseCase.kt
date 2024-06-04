package com.example.radiotest.domain


import com.example.radiotest.data.StationRepository
import com.example.radiotest.data.model.Available
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetRadioStationListUseCase @Inject constructor(private val stationRepository: StationRepository) {

     operator fun invoke(): Single<List<Station>> = stationRepository
        .getAllStations()
        .toObservable()
        .flatMapIterable { it }
//        .flatMap { radio ->
//            stationRepository
//                .getStationAvailability(radio.stationuuid)
//                .map { av -> Station(radio, av[0]) }
//                .toObservable()
//        }.toList()
         .map { Station(radioStation = it, Available(ok = 1)) }.toList()

}