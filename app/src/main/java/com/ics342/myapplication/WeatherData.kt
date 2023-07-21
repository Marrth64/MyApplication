package com.ics342.myapplication

import com.squareup.moshi.Json
/*
data class Temperature(
    val average: Double
    val high: Double,
    val low: Double,
)

data class WeatherCondition(
    val icon: String,
)

data class WeatherData(
    val conditionDescription: String,
    private val temperature: Temperature,
    @Json(name= "weather")
    private val weatherConditions: List<WeatherCondition>,
) {
    val highTemp: Int
        get() = temperature.high
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
data class Temperature(
    val average: Double,
    val high: Double,
    val low: Double,
)
data class WeatherCondition(
    val temperature: Temperature,
    val pressure:Int,
    val humidity: Int
)
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

}