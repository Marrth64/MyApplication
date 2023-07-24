package com.ics342.myapplication

import com.squareup.moshi.Json

data class ForecastData(
     @Json(name="cnt") val count:Int,
     @Json(name="list") val forecast: List<ForecastCondition>
)

data class ForecastCondition(
    @Json(name="dt") val date : Long,
    @Json(name="sunrise") val sunrise : Long,
    @Json(name="sunset") val sunset: Long,
    @Json(name="temp") val temp : ForestTemp,
    @Json(name="pressure") val pressure : Float,
    @Json(name="humidity") val humidity : Int
)
data class ForestTemp(
    val day : Float,
    val min : Float,
    val max: Float
)