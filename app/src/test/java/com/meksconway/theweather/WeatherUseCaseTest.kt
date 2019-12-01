package com.meksconway.theweather

import com.meksconway.theweather.interaction.WeatherUseCaseImpl
import com.meksconway.theweather.repository.WeatherRepository
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.runBlocking
import org.junit.Test

class WeatherUseCaseTest {


    private val weatherRepository: WeatherRepository = mock()
    private val getWeather by lazy {
        WeatherUseCaseImpl(weatherRepository)
    }

    @Test
    fun `test WeatherUseCase calls WeatherRepository`(){
        runBlocking {
            getWeather.invoke(CITY_NAME)
            verify(weatherRepository).getWeatherForLocation(CITY_NAME)
        }
    }


}