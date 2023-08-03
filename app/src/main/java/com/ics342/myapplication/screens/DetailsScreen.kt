package com.ics342.myapplication.screens

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.ics342.myapplication.Data.ForecastData
import com.ics342.myapplication.Data.dateConverter
import com.ics342.myapplication.Data.timeConverter
import com.ics342.myapplication.R
import com.ics342.myapplication.ViewModels.ForecastViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DetailsScreen(navController: NavHostController, forecastData: State<ForecastData?>) {
    Column(
        modifier= Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())

    ) {
        forecastData.value?.forecast?.forEachIndexed { index, forecast ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.padding(5.dp),

                ) {
                    ForecastConditionIcon(url = forecast.ForecastWeather[0].iconUrl)
                   // Log.d("ICON", forecast.ForecastWeather.iconUrl)

                    Text(
                        text = dateConverter(forecast.date),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(2.dp),
                    )
                }
                Column(
                    verticalArrangement = Arrangement.Center
                ) {
                    Text("${forecast.temp.day.toInt()}°")
                    Row {
                        Text("High: ${forecast.temp.max.toInt()}°")
                        Text("Low: ${forecast.temp.min.toInt()}°")
                    }
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(text = "Sunrise: ${timeConverter(forecast.sunrise)}")
                    Text(
                        text = "Sunset: ${timeConverter(forecast.sunset)}",
                    )
                }
            }
        }
        Button(onClick = { navController.navigate(Screens.Home.route) }) {
            Text("WeatherScreen")
        }

    }
}

@Composable
fun ForecastConditionIcon(
    url: String
) {
    AsyncImage(
        model = url,
        contentDescription = "",
        alignment = Alignment.Center,
        contentScale = ContentScale.FillBounds,
    )
}
@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun PreviewDetailScreen(){
    val forecastViewModel: ForecastViewModel = hiltViewModel()
    DetailsScreen(
        navController = rememberNavController(),
        forecastData = forecastViewModel.forecastData.observeAsState()
    )
}