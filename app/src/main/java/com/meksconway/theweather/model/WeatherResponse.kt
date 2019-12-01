package com.meksconway.theweather.model

import com.meksconway.theweather.db.WeatherEntity
import com.meksconway.theweather.networking.RoomMapper
import java.util.*

/*
* {
  "result": [
    {
      "date": "24.09.2018",
      "day": "Pazartesi",
      "icon": "https://image.flaticon.com/icons/svg/143/143769.svg",
      "description": "açık",
      "status": "Clear",
      "degree": "31",
      "min": "11.6",
      "max": "31",
      "night": "11.6",
      "humidity": "17"
    },
* */

data class WeatherResponse(val result: List<WeatherResultResponse>?, var cityName: String?) :
    RoomMapper<WeatherEntity> {

    override fun mapToRoomEntity(): WeatherEntity {
        return WeatherEntity(
            id = UUID.randomUUID().timestamp().toInt(),
            cityName = cityName ?: "ankara",
            weatherList = result ?: emptyList()
        )
    }

}

data class WeatherResultResponse(
    val date: String?,
    val day: String?,
    val icon: String?,
    val description: String?,
    val status: String?,
    val degree: String?,
    val min: String?,
    val max: String?,
    val night: String?,
    val humidity: String?
)


