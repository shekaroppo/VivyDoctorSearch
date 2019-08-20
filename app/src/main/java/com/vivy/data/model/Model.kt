package com.vivy.data.model

import com.google.gson.annotations.SerializedName

data class LoginResponse(
        @SerializedName("access_token")
        val accessToken: String
)

data class SearchResultResponse(
        val doctors: List<Doctor>,
        val lastKey: String
)

data class Doctor(
        val address: String,
        val id: String?,
        val name: String
)