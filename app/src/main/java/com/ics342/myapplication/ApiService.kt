package com.ics342.myapplication

import retrofit2.http.GET

interface ApiService {
    @GET("https://api.openweathermap.org/data/2.5/weather?zip=55112,us&appid=5acbc703c75dd2cdb073d1746b82e7e9")
    suspend fun getWeatherData(): WeatherData

    @GET("https://api.openweathermap.org/data/2.5/forecast/daily?zip=55112,us&appid=5acbc703c75dd2cdb073d1746b82e7e9")
    suspend fun getForecastData(): List<Forecast>
}
