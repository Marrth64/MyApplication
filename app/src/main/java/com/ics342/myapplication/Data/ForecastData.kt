package com.ics342.myapplication.Data

import android.os.Build
import androidx.annotation.RequiresApi
import com.squareup.moshi.Json
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.TimeZone


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

@RequiresApi(Build.VERSION_CODES.O)
fun dateConverter(DateLong: Long): String {
    val triggerTime = LocalDateTime.ofInstant(
        Instant.ofEpochSecond(DateLong),
        TimeZone.getDefault().toZoneId()
    )
    val date: LocalDate = triggerTime.toLocalDate()
    val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("MMM d")
    return date.format(formatter)
}
@RequiresApi(Build.VERSION_CODES.O)
fun timeConverter(DateLong: Long):String{
    val sunriseDateTime = LocalDateTime.ofInstant(
        Instant.ofEpochSecond(DateLong),
        ZoneId.systemDefault()
    )
    val formatter = DateTimeFormatter.ofPattern("h:mm a")
    val formattedTime = sunriseDateTime.format(formatter);
    return formattedTime
}

