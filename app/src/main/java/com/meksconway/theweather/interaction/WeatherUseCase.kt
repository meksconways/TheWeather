package com.meksconway.theweather.interaction

import com.meksconway.theweather.base.BaseUseCase
import com.meksconway.theweather.model.Result
import com.meksconway.theweather.model.WeatherResponse

@Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")
interface WeatherUseCase : BaseUseCase<String, WeatherResponse>{

    override suspend fun invoke(location: String): Result<WeatherResponse>

}