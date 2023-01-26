package com.example.music_mvp_ricardogiles.presenter

import androidx.lifecycle.LifecycleCoroutineScope
import com.example.music_mvp_ricardogiles.model.GeneralResponse
import com.example.music_mvp_ricardogiles.model.rest.MusicRepository
import com.example.music_mvp_ricardogiles.model.utils.DataType
import com.example.music_mvp_ricardogiles.model.utils.UIState
import kotlinx.coroutines.launch
import javax.inject.Inject

class ClassicPresenter @Inject constructor(
    private val repository: MusicRepository
): ClassicPresenterContract {

    private var view: ClassicViewContract? = null

    override fun getClassicData(coroutineScope: LifecycleCoroutineScope) {
        coroutineScope.launch{
            repository.gender.collect{
                when(it){
                    is UIState.ERROR -> { view?.error(it.e) }
                    is UIState.SUCCESS -> { view?.success(it.response) }
                    is UIState.LOADING -> { view?.loading() }
                }
            }
        }
        repository.getData(DataType.CLASSIC)
    }

    override fun initPresenter(view: ClassicViewContract) {
        this.view = view
    }

    override fun destroyPresenter() {
        view = null
    }


}

interface ClassicPresenterContract{
    fun getClassicData(coroutineScope: LifecycleCoroutineScope)
    fun initPresenter(view: ClassicViewContract)
    fun destroyPresenter()
}

interface ClassicViewContract{
    fun success(response: GeneralResponse)
    fun error(ex: Exception)
    fun loading()
}