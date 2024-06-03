package com.example.radiotest.data

import com.example.radiotest.data.model.Available
import com.example.radiotest.data.model.RadioStation
import com.example.radiotest.data.service.RadioService
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class StationRepositoryImpl @Inject constructor(
    private val radioService: RadioService
) : StationRepository {
    override fun getAllStations(): Single<List<RadioStation>> {
        return radioService
            .getAllRadioStations()
            .subscribeOn(Schedulers.io())
    }

    override fun getStationAvailability(stationuuid: String?): Single<List<Available>> {
        return radioService
            .getCurrentStationAvailability(stationuuid)
            .subscribeOn(Schedulers.io())
    }
}