package com.vivy.data.services

import com.vivy.data.model.*
import com.vivy.utils.Constants
import io.reactivex.Single
import retrofit2.http.*

interface ApiService {

    @FormUrlEncoded
    @POST("${Constants.OATH_URL}/oauth/token?grant_type=password")
    fun doLogin(@Field("username") username: String, @Field("password") password: String, @HeaderMap header: Map<String, @JvmSuppressWildcards Any>): Single<LoginResponse>

    @GET("/api/users/me/doctors")
    fun searchDoctors(@QueryMap(encoded = true) query: Map<String, @JvmSuppressWildcards Any>, @HeaderMap headers: Map<String, String>): Single<SearchResultResponse>

}