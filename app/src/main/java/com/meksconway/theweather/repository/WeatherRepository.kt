package com.meksconway.theweather.repository

import com.meksconway.theweather.model.Result
import com.meksconway.theweather.model.WeatherResponse

interface WeatherRepository {
    suspend fun getWeatherForLocation(location: String): Result<WeatherResponse>
}