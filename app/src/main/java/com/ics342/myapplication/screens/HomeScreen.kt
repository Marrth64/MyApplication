package com.ics342.myapplication.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ics342.myapplication.Data.WeatherData
import com.ics342.myapplication.ViewModels.WeatherViewModel


@Composable
fun HomeScreen(navController: NavController, weatherData: State<WeatherData?>){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ){
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center,

            ){
            Text(text = weatherData.value?.locName.toString() ?: "Seaseme Street")
        }
        Column {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()

            ){
                Text(
                    text =("${weatherData.value?.weatherCond?.average?.toInt()}°") ?: "65°",
                    fontSize = 100.sp
                )
                // Image(painter = painterResource(id = R.drawable.sunshine),
                //   contentDescription = "My Image",
                //    alignment = Alignment.Center,
                //   contentScale = ContentScale.FillBounds,
                //   modifier = Modifier.size(150.dp)

            }
            Text("Feels Like: ${weatherData.value?.weatherCond?.feel?.toInt()}°"
                ?: "Feels Like: 67°")
        }
        Text("Low: ${weatherData.value?.weatherCond?.low?.toInt()}°"
            ?: "Low : 23°")
        Text("High: ${weatherData.value?.weatherCond?.high?.toInt()}°"
            ?: "High : 76°"
        )
        Text("Humidity: ${weatherData.value?.weatherCond?.humidity}%"
            ?: "Humidity: 67%")
        Text("Pressure: ${weatherData.value?.weatherCond?.pressure} mPa"
            ?: "Pressure: 1023 mPa")
        Button(onClick = { navController.navigate(Screens.Details.route) }) {
            Text("Forecast")
        }
    }

}

@Preview
@Composable 
fun PreviewHomeScreen(weatherViewModel:WeatherViewModel = hiltViewModel()){
    HomeScreen(
        navController = rememberNavController(),
        weatherData = weatherViewModel.weatherData.observeAsState()
    )
}
