package com.example.music_mvp_ricardogiles.di

import com.example.music_mvp_ricardogiles.model.rest.MusicRepository
import com.example.music_mvp_ricardogiles.model.rest.MusicRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule {

    @Binds
    abstract fun bindsMusicsRepository(
        musicRepositoryImpl: MusicRepositoryImpl
    ): MusicRepository
}