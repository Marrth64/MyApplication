package com.ics342.myapplication.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.ics342.myapplication.Data.WeatherData
import com.ics342.myapplication.R
import com.ics342.myapplication.ViewModels.WeatherViewModel


@Composable
fun HomeScreen(navController: NavController, weatherData: State<WeatherData?>) {

    weatherData.value?.let {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
        ) {

            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center,

                ) {
                Text(text = it.locName.toString() ?: "Seaseme Street")
            }
            Column {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()

                ) {
                    Text(
                        text = ("${it.weatherCond.average.toInt()}°") ?: "65°",
                        fontSize = 100.sp
                    )
                    WeatherConditionIcon(url = it.weatherIcon[0].iconUrl)
                    Log.d("URL",it.weatherIcon[0].iconUrl)
                }
            }
            Text(
                "Feels Like: ${it.weatherCond.feel.toInt()}°"
                    ?: "Feels Like: 67°"
            )

        Text(
            "Low: ${it.weatherCond.low.toInt()}°"
                ?: "Low : 23°"
        )
        Text(
            "High: ${it.weatherCond.high.toInt()}°"
                ?: "High : 76°"
        )
        Text(
            "Humidity: ${it.weatherCond.humidity}%"
                ?: "Humidity: 67%"
        )
        Text(
            "Pressure: ${it.weatherCond.pressure} mPa"
                ?: "Pressure: 1023 mPa"
        )
        Button(onClick = { navController.navigate(Screens.Details.route) }) {
            Text("Forecast")
        }
        Button(onClick = { /*TODO*/ }) {
            Text("Search")
        }
    }

}
    }



@Composable
fun WeatherConditionIcon(
    url: String
) {
    AsyncImage(
        model = url,
        contentDescription = "",
        alignment = Alignment.Center,
        contentScale = ContentScale.FillBounds,
    )
}

@Preview
@Composable 
fun PreviewHomeScreen(weatherViewModel:WeatherViewModel = hiltViewModel()){
    HomeScreen(
        navController = rememberNavController(),
        weatherData = weatherViewModel.weatherData.observeAsState()
    )
}
