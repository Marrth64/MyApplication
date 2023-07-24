package com.ics342.myapplication.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ics342.myapplication.Data.ForecastData
import com.ics342.myapplication.Data.dateConverter
import com.ics342.myapplication.Data.timeConverter
import com.ics342.myapplication.ViewModels.ForecastViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DetailsScreen(navController: NavHostController, forecastData: State<ForecastData?>) {
    Column {
        forecastData.value?.forecast?.forEachIndexed { index, forecast ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                //horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.padding(2.dp)
                ) {
                    Text(
                        text = dateConverter(forecast.date),
                        textAlign = TextAlign.Center,
                     //   modifier = Modifier.padding(2.dp),
                      //  fontSize = 10.sp
                    )
                }
                Column(
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "${forecast.temp.day.toInt()}°",
                      //  fontSize = 7.sp
                    )
                    Row {
                        Text(
                            text = "High: ${forecast.temp.max.toInt()}°",
                        //    fontSize = 7.sp
                        )
                        Text(
                            text = "Low: ${forecast.temp.min.toInt()}°",
                         //   fontSize = 7.sp
                        )
                    }
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Sunrise: ${timeConverter(forecast.sunrise)}",
                   //     fontSize = 7.sp
                    )
                    Text(
                        text = "Sunset: ${timeConverter(forecast.sunset)}",
                    //    fontSize = 7.sp
                    )
                }
            }
        }
        Button(onClick = { navController.navigate(Screens.Home.route) }) {
            Text("WeatherScreen")
        }

    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun PreviewDetailScreen(){
    val forecastViewModel: ForecastViewModel = hiltViewModel()
    DetailsScreen(navController = rememberNavController(), forecastData = forecastViewModel.forecastData.observeAsState())
}