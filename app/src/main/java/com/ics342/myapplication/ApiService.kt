package com.ics342.myapplication

import com.ics342.myapplication.Data.ForecastData
import com.ics342.myapplication.Data.WeatherData
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("weather")
    suspend fun getWeatherData(
        @Query("zip") zip:String,
        @Query("units") units:String = "imperial",
        @Query("appid") appId:String = "5acbc703c75dd2cdb073d1746b82e7e9"
    ): WeatherData

    @GET("forecast/daily?")
    suspend fun getForecastData(
        @Query("zip") zip:String,
        @Query("units") units:String = "imperial",
        @Query("cnt") cnt: Int = 16,
        @Query("appid") appId:String = "5acbc703c75dd2cdb073d1746b82e7e9"
    ): ForecastData
}
