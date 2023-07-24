package com.ics342.myapplication

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.ics342.myapplication.ui.theme.MyApplicationTheme
import dagger.hilt.android.AndroidEntryPoint
import java.time.Instant
import java.time.LocalDateTime
import java.util.TimeZone
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyApp()
                }
            }
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarLayout(title:String){
    TopAppBar(
        title = { Text(
            text = title,
            color = Color.DarkGray)},
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = Color.LightGray
        ),
    )
}
@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun MyApp(
    weatherViewModel: WeatherViewModel = hiltViewModel(),
    forecastViewModel: ForecastViewModel = hiltViewModel()
) {
        val navController = rememberNavController()
        val weatherData = weatherViewModel.weatherData.observeAsState()
        val forecastData = forecastViewModel.forecastData.observeAsState()
        LaunchedEffect(Unit) {
            weatherViewModel.viewAppeared()
                forecastViewModel.viewAppeared()
        }
      //  Log.d("JSON",forecastViewModel.viewAppeared().toString())
        val currentDestination = navController.currentBackStackEntry?.destination?.route
        //Log.d("sunrise=", forecastData.value?.forecast?.get(0)?.sunrise.toString() ?: "6:00 a.m")
    //Log.d("COUNT = ", forecastData.value?.count.toString() ?: "6:00 a.m")

    Scaffold(
            topBar = {
                when (currentDestination) {
                    Screen.Home.route -> {
                        TopBarLayout("Weather App") }
                    Screen.Details.route -> {
                        TopBarLayout("Forecast") }
                }
            },
            content = {
                NavHost(navController, startDestination = "main_screen") {
                    composable(Screen.Home.route) { HomeScreen(navController, weatherData) }
                    composable(Screen.Details.route) { DetailsScreen(navController,forecastData) }
                }
            }
        )



    /*

    */
    }

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DetailsScreen(navController: NavHostController, forecastData: State<ForecastData?>) {
    Column {
        forecastData.value?.forecast?.forEachIndexed { index, forecast ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.padding(2.dp)
                ) {
                    //Image(
                    //   painter = painterResource(id = R.drawable.sunshine),
                    //    contentDescription = "My Image",
                    //    alignment = Alignment.Center,
                    //    contentScale = ContentScale.FillBounds,
                    // )
                    Text(
                        text =DateConverter(forecast.date),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(2.dp),
                        fontSize = 10.sp
                    )
                }
                Column(
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "${forecast.temp.day.toInt()}°",
                        fontSize = 7.sp
                    )
                    Row() {
                        Text(
                            text = "High: ${forecast.temp.max.toInt()}°",
                            fontSize = 7.sp
                        )
                        Text(
                            text = "Low: ${forecast.temp.min.toInt()}°",
                            fontSize = 7.sp
                        )
                    }
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Sunrise: ${timeConverter(forecast.sunrise)}",
                        fontSize = 7.sp
                    )
                    Text(
                        text = "Sunset: ${timeConverter(forecast.sunset)}",
                        fontSize = 7.sp
                    )
                }
            }
        }
        Button(onClick = { navController.navigate(Screen.Home.route) }) {
            Text("WeatherScreen")
        }

    }




}


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
        Button(onClick = { navController.navigate(Screen.Details.route) }) {
           Text("Forecast")
         }
    }

}

sealed class Screen(val route: String){
    object Home: Screen(route="main_screen")
    object Details: Screen(route="forecast_screen")

}
@Composable
fun WeatherConditionIcon(url: String) {
    AsyncImage(model = url, contentDescription = "")
}
