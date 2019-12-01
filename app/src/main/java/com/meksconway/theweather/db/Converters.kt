package com.meksconway.theweather.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.meksconway.theweather.model.WeatherResultResponse

class Converters {

    val gson = Gson()

    @TypeConverter
    fun fromWeatherListToJson(list: List<WeatherResultResponse>?): String {
        return list?.let {
            gson.toJson(it)
        } ?: ""
    }

    @TypeConverter
    fun fromJsonToWeatherList(jsonList: String): List<WeatherResultResponse> {
        val listType = object : TypeToken<List<WeatherResultResponse>>() {}.type
        return gson.fromJson(jsonList, listType)
    }



}