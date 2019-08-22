package com.vivy.data.services

import com.vivy.data.model.*
import com.vivy.utils.Constants
import io.reactivex.Single
import retrofit2.http.*

interface ApiService {

    @GET("5d5d37e34acbd77681dce3c2")
    fun getRestaurants(): Single<RestaurantResponse>

}