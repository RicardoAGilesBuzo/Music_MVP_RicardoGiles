package com.example.music_mvp_ricardogiles.model.rest

import android.util.Log
import com.example.music_mvp_ricardogiles.model.utils.DataType
import com.example.music_mvp_ricardogiles.model.utils.UIState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "MusicRepository"

interface MusicRepository {
    val gender: StateFlow<UIState>
    fun getData(dataType: DataType)
}

class MusicRepositoryImpl @Inject constructor(
    private val coroutineScope: CoroutineScope,
    private val serviceAPI: MusicAPI
): MusicRepository {
    private val _gender: MutableStateFlow<UIState> = MutableStateFlow(UIState.LOADING)
    override val gender: StateFlow<UIState> get() = _gender

    override fun getData(dataType: DataType) {
        when(dataType) {
            DataType.ROCK -> {
                getMusic(DataType.ROCK.toString())
            }
            DataType.POP -> {
                getMusic(DataType.POP.toString())
            }
            DataType.CLASSIC -> {
                getMusic(DataType.CLASSIC.toString())
            }
        }
    }

    private fun getMusic(musicTerm: String){
        _gender.value = UIState.LOADING

        coroutineScope.launch {
            try {
                val response = serviceAPI.getGender(musicTerm)
                if (response.isSuccessful){
                    response.body()?.let {
                        _gender.value = UIState.SUCCESS(it)
                    }
                    Log.d(TAG, "getMusic: ${response.message()}")
                } else{
                    Log.d(TAG, "getMusic: ${response.message()}")
                }
            }catch (e: Exception){
                Log.d(TAG, "getMusic: ${e.message}", e)
            }
        }
    }
}