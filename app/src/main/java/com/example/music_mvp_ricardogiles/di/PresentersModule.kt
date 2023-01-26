package com.example.music_mvp_ricardogiles.di

import com.example.music_mvp_ricardogiles.presenter.*
import dagger.Binds
import dagger.Module

@Module
abstract class PresentersModule {

    @Binds
    abstract fun providesRockPresenter(
        peoplePresenter: RockPresenter
    ): RockPresenterContract

    @Binds
    abstract fun providesPopPresenter(
        planetsPresenter: PopPresenter
    ): PopPresenterContract

    @Binds
    abstract fun providesClassicPresenter(
        starshipPresenter: ClassicPresenter
    ): ClassicPresenterContract
}