package com.ics342.myapplication.Data

import com.squareup.moshi.Json


data class WeatherData(
    @Json(name= "name") val locName:String,
    @Json(name="main")  val weatherCond: WeatherCondition,
    @Json(name="weather") val weatherIcon: List<WeatherIcon>
)

data class WeatherCondition(
    @Json(name="temp") val average: Double,
    @Json(name="feels_like") val feel:Double,
    @Json(name="temp_max") val high: Double,
    @Json(name="temp_min") val low: Double,
    @Json(name="pressure") val pressure: Int,
    @Json(name="humidity") val humidity: Int
)

data class WeatherIcon(
    @Json(name="icon") val icon:String
){
    val iconUrl:String
        get() = "https://openweathermap.org/img/wn/${icon}@2x.png"
}
