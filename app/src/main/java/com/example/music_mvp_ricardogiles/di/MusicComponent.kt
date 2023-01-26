package com.example.music_mvp_ricardogiles.di

import com.example.music_mvp_ricardogiles.MainActivity
import com.example.music_mvp_ricardogiles.view.ClassicFragment
import com.example.music_mvp_ricardogiles.view.PopFragment
import com.example.music_mvp_ricardogiles.view.RockFragment
import dagger.Component

@Component(modules = [
    NetworkModule::class,
    RepositoryModule::class,
    ApplicationModule::class,
    PresentersModule::class
])
interface MusicComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(rockFragment: RockFragment)
    fun inject(popFragment: PopFragment)
    fun inject(classicFragment: ClassicFragment)
}