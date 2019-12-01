package com.meksconway.theweather.base

import com.meksconway.theweather.BuildConfig
import com.meksconway.theweather.model.Failure
import com.meksconway.theweather.model.HttpError
import com.meksconway.theweather.model.Success
import com.meksconway.theweather.util.Connectivity
import com.meksconway.theweather.util.CoroutineContextProvider
import com.meksconway.theweather.model.Result
import com.meksconway.theweather.networking.DomainMapper
import kotlinx.coroutines.withContext
import org.koin.core.KoinComponent
import org.koin.core.inject

abstract class BaseRepository<T : Any, R : DomainMapper<T>> : KoinComponent {
    private val connectivity: Connectivity by inject()
    private val contextProvider: CoroutineContextProvider by inject()

    protected suspend fun fetchData(
        apiDataProvider: suspend () -> Result<T>,
        dbDataProvider: suspend () -> R
    ): Result<T> {
        return if (connectivity.hasNetworkAccess()) {
            withContext(contextProvider.io) {
                apiDataProvider()
            }
        } else {
            withContext(contextProvider.io) {
                val dbResult = dbDataProvider()
                @Suppress("SENSELESS_COMPARISON")
                if (dbResult != null) Success(dbResult.mapToDomainModel())
                else Failure(HttpError(Throwable(BuildConfig.DB_ENTRY_ERROR)))
            }
        }
    }

    protected suspend fun fetchData(dataProvider: () -> Result<T>): Result<T> {
        return if (connectivity.hasNetworkAccess()) {
            withContext(contextProvider.io) {
                dataProvider()
            }
        } else {
            Failure(HttpError(Throwable(BuildConfig.GENERAL_NETWORK_ERROR)))
        }
    }
}