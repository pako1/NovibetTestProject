package com.example.novibettestproject.data.remote.models

import com.squareup.moshi.Json

data class Token(
    @Json(name = "access_token")
    val value: String?
)