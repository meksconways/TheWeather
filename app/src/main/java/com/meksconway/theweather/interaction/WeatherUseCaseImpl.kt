package com.meksconway.theweather.interaction

import com.meksconway.theweather.model.Result
import com.meksconway.theweather.model.WeatherResponse
import com.meksconway.theweather.repository.WeatherRepository

class WeatherUseCaseImpl(
    private val repository: WeatherRepository
) : WeatherUseCase {

    override suspend fun invoke(location: String): Result<WeatherResponse> {
        return repository.getWeatherForLocation(location)
    }

}