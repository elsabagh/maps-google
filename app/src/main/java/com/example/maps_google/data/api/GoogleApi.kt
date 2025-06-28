package com.example.maps_google.data.api

import com.example.maps_google.data.model.RouteResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface GoogleApi {
    @GET("directions/json")
    suspend fun getDirections(
        @Query("origin") origin: String,
        @Query("destination") destination: String,
        @Query("key") key: String,
    ): RouteResponse
}