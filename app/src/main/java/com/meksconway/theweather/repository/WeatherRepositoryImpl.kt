package com.meksconway.theweather.repository

import com.meksconway.theweather.base.BaseRepository
import com.meksconway.theweather.db.WeatherDao
import com.meksconway.theweather.db.WeatherEntity
import com.meksconway.theweather.model.Result
import com.meksconway.theweather.model.WeatherResponse
import com.meksconway.theweather.networking.WeatherApi
import com.meksconway.theweather.networking.getData

class WeatherRepositoryImpl(
    private val weatherApi: WeatherApi,
    private val weatherDao: WeatherDao
) : BaseRepository<WeatherResponse, WeatherEntity>(), WeatherRepository {


    override suspend fun getWeatherForLocation(location: String): Result<WeatherResponse> {
        return fetchData(
            apiDataProvider = {
                weatherApi.getWeather(location).getData(
                    fetchFromCacheAction = { weatherDao.getWeatherForCity(location) },
                    cacheAction = { weatherDao.saveWeatherInfo(it) })
            },
            dbDataProvider = { weatherDao.getWeatherForCity(location) }
        )
    }
}



