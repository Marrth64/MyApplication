package com.ics342.myapplication

import com.squareup.moshi.Json
/*
data class Temperature(
    @Json(name="temp") val average: Double,
    @Json(name="feels_like") val feel:Double,
    @Json(name="temp_max") val high: Double,
    @Json(name="temp_min") val low: Double,
)

data class WeatherCondition(
    val icon: String,
)

data class WeatherData(

    @Json(name= "name") private val weatherConditions: List<WeatherCondition>,
) {
    val highTemp: Int
        get() = temperature.high
    val lowTemp: Int
        get() = temperature.low
    val averageTemp: Int
        get() = temperature.average
    val iconUrl: String
        get() = "https://openweathermap.org/img/wn/${weatherConditions.firstOrNull()?.icon}@2x.png"
}

data class ForecastItem(
    @Json(name = "weather")
    val weatherConditions: List<WeatherCondition>
) {
    val iconUrl: String
        get() = "https://openweathermap.org/img/wn/${weatherConditions.firstOrNull()?.icon}@2x.png"
}

data class Forecast(
    @Json(name = "list")
    val forecasts: List<ForecastItem>
)
*/

data class WeatherData(
    @Json(name= "name") val locName:String,
    @Json(name="main")  val weatherCond:WeatherCondition
)


data class WeatherCondition(
    @Json(name="temp") val average: Double,
    @Json(name="feels_like") val feel:Double,
    @Json(name="temp_max") val high: Double,
    @Json(name="temp_min") val low: Double,
    @Json(name="pressure") val pressure: Int,
    @Json(name="humidity") val humidity: Int
)

/*
data class WeatherData(
    val conditionDescription: String,
    private val temperature: Temperature,
    @Json(name= "main")
    private val weatherConditions: List<WeatherCondition>,
) {
    val avgTemp:Double
        get() = temperature.average
    val maxTemp:Double
        get() = temperature.high
    val lowTemp:Double
        get()=temperature.low
}*/