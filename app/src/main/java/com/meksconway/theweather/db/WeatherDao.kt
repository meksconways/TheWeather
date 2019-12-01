package com.meksconway.theweather.db

import androidx.room.*
import com.meksconway.theweather.BuildConfig

@Dao
interface WeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveWeatherInfo(weather: WeatherEntity)

    @Query("SELECT * FROM " + BuildConfig.DB_NAME + " WHERE cityName = :cityName")
    suspend fun getWeatherForCity(cityName: String) : WeatherEntity

    @Transaction
    suspend fun updateWeatherAndReturn(weather: WeatherEntity): WeatherEntity{
        saveWeatherInfo(weather)
        return getWeatherForCity(weather.cityName ?: "")
    }




}