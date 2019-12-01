package com.meksconway.theweather

import com.meksconway.theweather.db.WeatherEntity
import com.meksconway.theweather.model.WeatherResponse
import okhttp3.MediaType
import okhttp3.ResponseBody

const val CITY_NAME = "İstanbul"
const val FAKE_FAILURE_ERROR_CODE = 400

val successWeatherInfoResponse = WeatherResponse(arrayListOf(), CITY_NAME)
val failureResponseBody = ResponseBody.create(MediaType.parse("text"), "network error")
val fakeWeatherEntity = WeatherEntity(0, arrayListOf(), CITY_NAME)