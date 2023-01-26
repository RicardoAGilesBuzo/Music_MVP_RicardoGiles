package com.example.music_mvp_ricardogiles.presenter

import androidx.lifecycle.LifecycleCoroutineScope
import com.example.music_mvp_ricardogiles.model.GeneralResponse
import com.example.music_mvp_ricardogiles.model.rest.MusicRepository
import com.example.music_mvp_ricardogiles.model.utils.DataType
import com.example.music_mvp_ricardogiles.model.utils.UIState
import kotlinx.coroutines.launch
import javax.inject.Inject

class RockPresenter @Inject constructor(
    private val repository: MusicRepository
) : RockPresenterContract {

    private var rockViewContract: RockViewContract? = null

    override fun initPresenter(viewContract: RockViewContract) {
        rockViewContract = viewContract
    }

    override fun getRockData(lifecycleCoroutineScope: LifecycleCoroutineScope) {
        lifecycleCoroutineScope.launch {
            repository.gender.collect {
                when (it) {
                    is UIState.LOADING -> { rockViewContract?.loading() }
                    is UIState.SUCCESS -> { rockViewContract?.success(it.response) }
                    is UIState.ERROR -> { rockViewContract?.error(it.e) }
                }
            }
        }

        repository.getData(DataType.ROCK)
    }

    override fun destroyPresenter() {
        rockViewContract = null
    }
}

interface RockViewContract {
    fun loading()
    fun success(response: GeneralResponse)
    fun error(e: Exception)
}

interface RockPresenterContract {
    fun initPresenter(viewContract: RockViewContract)
    fun getRockData(lifecycleCoroutineScope: LifecycleCoroutineScope)
    fun destroyPresenter()
}