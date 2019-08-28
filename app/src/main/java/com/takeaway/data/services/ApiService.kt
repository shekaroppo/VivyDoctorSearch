package com.takeaway.data.services

import com.takeaway.data.model.RestaurantResponse
import retrofit2.http.GET

interface ApiService {

    @GET("5d5d37e34acbd77681dce3c2")
    suspend fun getRestaurants(): RestaurantResponse

}