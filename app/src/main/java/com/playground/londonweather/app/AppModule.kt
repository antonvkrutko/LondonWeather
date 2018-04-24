package com.playground.londonweather.app

import android.arch.persistence.room.Room
import android.content.Context
import com.playground.londonweather.BuildConfig
import com.playground.londonweather.model.api.WeatherApi
import com.playground.londonweather.model.persistent.ConditionsDao
import com.playground.londonweather.model.persistent.ConditionsDatabase
import com.playground.londonweather.model.repo.CachedOpenWeatherRepo
import com.playground.londonweather.model.repo.CachedWeatherRepo
import com.playground.londonweather.model.repo.OpenWeatherRepo
import com.playground.londonweather.model.repo.WeatherRepo
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier

@Qualifier
annotation class AuthInterceptor

@Qualifier
annotation class LoggingInterceptor

@Module
class AppModule(
        private val app: App
) {

    @AppScope
    @Provides
    fun provideCachedWeatherRepo(
            weatherRepo: WeatherRepo,
            conditionsDao: ConditionsDao
    ): CachedWeatherRepo {
        return CachedOpenWeatherRepo(weatherRepo, conditionsDao)
    }

    @AppScope
    @Provides
    fun provideWeatherRepo(weatherApi: WeatherApi): WeatherRepo {
        return OpenWeatherRepo(weatherApi)
    }

    @AppScope
    @Provides
    fun provideWeatherApi(retrofit: Retrofit): WeatherApi {
        return retrofit.create(WeatherApi::class.java)
    }

    @AppScope
    @Provides
    fun providerRetrofit(httpClient: OkHttpClient, baseUrl: String): Retrofit {
        return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(httpClient)
                .baseUrl(baseUrl)
                .build()
    }

    @AppScope
    @Provides
    fun provideBaseUrl(): String {
        return "http://api.openweathermap.org/data/2.5/"
    }

    @AppScope
    @Provides
    fun provideClient(@AuthInterceptor authInterceptor: Interceptor,
                      @LoggingInterceptor loggingInterceptor: Interceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
                .addInterceptor(authInterceptor)
                .addInterceptor(loggingInterceptor)
                .build()

    }

    @AppScope
    @Provides
    @AuthInterceptor
    fun provideAuthInterceptor(): Interceptor {
        return Interceptor {
            with(it.request()) {
                val newUrl = url().newBuilder()
                        .addQueryParameter("APPID", BuildConfig.APPID)
                        .build()
                it.proceed(newBuilder().url(newUrl).build())
            }
        }
    }

    @AppScope
    @Provides
    @LoggingInterceptor
    fun provideLoggingInterceptor(): Interceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @AppScope
    @Provides
    fun provideConditionsDatabase(context: Context): ConditionsDatabase {
        return Room.databaseBuilder(context, ConditionsDatabase::class.java, "conditionsDb")
                .build()
    }

    @AppScope
    @Provides
    fun provideConditionsDao(conditionsDatabase: ConditionsDatabase): ConditionsDao {
        return conditionsDatabase.conditionsDao
    }

    @AppScope
    @Provides
    fun provideContext(): Context {
        return app
    }

    @AppScope
    @Provides
    fun provideSchedulers(): Schedulers {
        return AppSchedulers()
    }
}