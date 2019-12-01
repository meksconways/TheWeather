package com.meksconway.theweather.di

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import androidx.room.Room
import com.meksconway.theweather.BuildConfig
import com.meksconway.theweather.db.WeatherDatabase
import com.meksconway.theweather.interaction.WeatherUseCase
import com.meksconway.theweather.interaction.WeatherUseCaseImpl
import com.meksconway.theweather.networking.WeatherApi
import com.meksconway.theweather.repository.WeatherRepository
import com.meksconway.theweather.ui.routing.AppFragmentNavigator
import com.meksconway.theweather.ui.routing.AppNavigator
import com.meksconway.theweather.util.Connectivity
import com.meksconway.theweather.util.ConnectivityImpl
import com.meksconway.theweather.util.CoroutineContextProvider
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.time.Duration
import java.util.concurrent.TimeUnit

val databaseModule = module {

    single {
        Room.databaseBuilder(
            androidContext(), WeatherDatabase::class.java, BuildConfig.DB_NAME
        ).fallbackToDestructiveMigration().build()
    }

    factory {
        get<WeatherDatabase>().weatherDao()
    }
}

val networkingModule = module {

    single {
        GsonConverterFactory.create() as Converter.Factory
    }

    single {
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY) as Interceptor
    }

    single {
        OkHttpClient.Builder().apply {
            if (BuildConfig.DEBUG) addInterceptor(get())
                .connectTimeout(30, TimeUnit.SECONDS)
                .callTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .addInterceptor {
                    val request = it.request().newBuilder()
                        .addHeader("Authorization", BuildConfig.API_KEY)
                        .addHeader("Accept", "application/json")
                        .addHeader("Content-Type", "application/json")
                        .build()
                    it.proceed(request)
                }
        }.build()
    }

    single {

        Retrofit.Builder()
            .baseUrl(BuildConfig.API_ADDRESS)
            .addConverterFactory(get())
            .client(get())
            .build()

    }

    single {
        get<Retrofit>().create(WeatherApi::class.java)
    }

}

val interactionModule = module {

    factory<WeatherUseCase> {
        WeatherUseCaseImpl(get())
    }

}

val repositoryModule = module {

    factory<WeatherRepository> {
        TODO()
    }

    factory<Connectivity> {
        ConnectivityImpl(get())
    }

}

val appModule = module {
    single {
        CoroutineContextProvider()
    }
    single { (activity: AppCompatActivity) ->
        AppNavigator(activity)
    }
    single { (activity: FragmentActivity) ->
        AppFragmentNavigator(activity)
    }

}

