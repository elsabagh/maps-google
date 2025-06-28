package com.example.maps_google.di

import com.example.maps_google.data.api.GoogleApi
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@ComponentScan("com.example.maps_google")
class AppModule {

    @Single
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl("https://maps.googleapis.com/maps/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Single
    fun provideGoogleApi(retrofit: Retrofit): GoogleApi =
        retrofit.create(GoogleApi::class.java)
}