package com.example.radiotest.di

import com.example.radiotest.data.StationRepository
import com.example.radiotest.data.StationRepositoryImpl
import dagger.Module

@Module
abstract class RepositoryModule {

    abstract fun bindStationRepository(stationRepositoryImpl: StationRepositoryImpl): StationRepository
}