package com.example.music_mvp_ricardogiles

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.music_mvp_ricardogiles.databinding.ActivityMainBinding
import com.example.music_mvp_ricardogiles.di.MusicApp

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        supportActionBar?.hide()
        MusicApp.musicComponent.inject(this)

        val navController = supportFragmentManager.findFragmentById(R.id.frag_container) as NavHostFragment

        binding.musicBt.setupWithNavController(navController.navController)
    }
}