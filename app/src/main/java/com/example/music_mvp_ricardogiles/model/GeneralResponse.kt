package com.example.music_mvp_ricardogiles.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GeneralResponse(
    @Json(name = "results")
    val results: List<Songs>
)

@JsonClass(generateAdapter = true)
data class Songs(
    @Json(name = "artistName")
    val artistName: String? = null,
    @Json(name = "collectionName")
    val collectionName: String? = null,
    @Json(name = "trackName")
    val trackName: String? = null,
    @Json(name = "artworkUrl100")
    val artworkUrl100: String? = null,
    @Json(name = "previewUrl")
    val previewUrl: String? = null
)
