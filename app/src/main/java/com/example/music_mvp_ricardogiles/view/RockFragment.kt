package com.example.music_mvp_ricardogiles.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.music_mvp_ricardogiles.MusicDetail
import com.example.music_mvp_ricardogiles.databinding.FragmentGeneralBinding
import com.example.music_mvp_ricardogiles.di.MusicApp
import com.example.music_mvp_ricardogiles.model.GeneralResponse
import com.example.music_mvp_ricardogiles.presenter.RockPresenterContract
import com.example.music_mvp_ricardogiles.presenter.RockViewContract
import com.example.music_mvp_ricardogiles.view.adapter.MusicAdapter
import javax.inject.Inject

private const val TAG = "RockFragment"

class RockFragment : Fragment(), RockViewContract {

    private val binding by lazy {
        FragmentGeneralBinding.inflate(layoutInflater)
    }

    private val adapter: MusicAdapter by lazy {
        MusicAdapter(mutableListOf()) { music ->
            with(Intent(requireContext(), MusicDetail::class.java)){
                putExtra("ALBUM_EXTRA", music.collectionName)
                putExtra("SONG_EXTRA", music.trackName)
                putExtra("COVER_EXTRA", music.artworkUrl100)
                putExtra("SONG_URL_EXTRA", music.previewUrl)
                putExtra("SONG_ARTIST", music.artistName)

                startActivity(this)
            }
        }
    }

    @Inject
    lateinit var presenter: RockPresenterContract

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MusicApp.musicComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        presenter.initPresenter(this)
        initViews()
        presenter.getRockData(lifecycleScope)

        return binding.root
    }

    private fun initViews() {
        binding.apply {
            listResponse.layoutManager = LinearLayoutManager(context)
            listResponse.adapter = adapter
        }
    }

    override fun onStop() {
        super.onStop()
        presenter.destroyPresenter()
    }

    override fun loading() {
        //
    }

    override fun success(response: GeneralResponse) {
        Log.d(TAG, "success: $response")
        adapter.updateDataSet(response.results)
    }

    override fun error(e: Exception) {
        Log.e(TAG, "error: ${e.message}", e)
    }
}