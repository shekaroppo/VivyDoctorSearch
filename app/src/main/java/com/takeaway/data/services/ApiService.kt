package com.takeaway.data.services

import com.takeaway.data.model.*
import io.reactivex.Single
import retrofit2.http.*

interface ApiService {

    @GET("5d5d37e34acbd77681dce3c2")
    suspend fun getRestaurants(): RestaurantResponse

}