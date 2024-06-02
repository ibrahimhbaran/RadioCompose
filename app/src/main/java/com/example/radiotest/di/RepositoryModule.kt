package com.example.radiotest.di

import com.example.radiotest.data.StationRepository
import com.example.radiotest.data.StationRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindStationRepository(stationRepositoryImpl: StationRepositoryImpl): StationRepository
}