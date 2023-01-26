package com.example.music_mvp_ricardogiles.model.rest

import com.example.music_mvp_ricardogiles.model.GeneralResponse
import com.example.music_mvp_ricardogiles.model.utils.*
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MusicAPI {
    @GET(ENDPOINT)
    suspend fun getGender(
        @Query(TERM) musicGender: String
    ): Response<GeneralResponse>
}