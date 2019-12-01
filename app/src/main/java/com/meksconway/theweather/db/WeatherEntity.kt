package com.meksconway.theweather.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.meksconway.theweather.BuildConfig
import com.meksconway.theweather.model.WeatherResponse
import com.meksconway.theweather.model.WeatherResultResponse
import com.meksconway.theweather.networking.DomainMapper

@Entity(tableName = BuildConfig.DB_NAME)
@TypeConverters(Converters::class)
data class WeatherEntity(
    @PrimaryKey
    val id: Int = 0,
    val weatherList: List<WeatherResultResponse>?,
    val cityName: String?
) : DomainMapper<WeatherResponse> {

    override fun mapToDomainModel(): WeatherResponse {
        return WeatherResponse(weatherList ?: emptyList(), cityName ?: "null")
    }


}