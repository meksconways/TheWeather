package com.meksconway.theweather.networking

import retrofit2.http.GET
import retrofit2.http.Query
import com.meksconway.theweather.model.Result
import com.meksconway.theweather.model.WeatherResponse
import retrofit2.Response

interface WeatherApi {

    @GET("getWeather")
    suspend fun getWeather(@Query("data.city") location: String)
            : Response<WeatherResponse>

}