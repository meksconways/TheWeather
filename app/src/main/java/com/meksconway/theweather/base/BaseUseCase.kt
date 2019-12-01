package com.meksconway.theweather.base
import com.meksconway.theweather.model.Result

interface BaseUseCase<T : Any, R: Any> {
    suspend operator fun invoke(param: T): Result<R>
}