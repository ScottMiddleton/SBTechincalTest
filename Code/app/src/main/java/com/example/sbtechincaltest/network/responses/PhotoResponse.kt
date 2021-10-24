package com.example.sbtechincaltest.network.responses

import androidx.annotation.Keep

@Keep
data class PhotoResponse(
    val albumId: String,
    val id: String,
    val title: String,
    val url: String,
    val thumbnailUrl: String
)
