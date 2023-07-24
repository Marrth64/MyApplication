package com.ics342.myapplication

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
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
        Log.d("sunrise=", forecastData.value?.forecast?.get(0)?.sunrise.toString() ?: "6:00 a.m")
    Log.d("COUNT = ", forecastData.value?.count.toString() ?: "6:00 a.m")

    Scaffold(
            topBar = {
                when (currentDestination) {
                    "main_screen" -> {
                        TopBarLayout("Weather App") }
                    "forecast_screen" -> {
                        TopBarLayout("Forecast") }
                }
            },
            content = {
                NavHost(navController, startDestination = "main_screen") {
                    composable("main_screen") { HomeScreen(navController, weatherData) }
                    composable("forecast_screen") { DetailsScreen(navController,forecastData) }
                }
            }
        )



    /*

    */
    }

@Composable
fun DetailsScreen(navController: NavHostController, forecastData: State<ForecastData?>) {
     Text("Hello World")
     Text(text = forecastData.value?.forecast?.get(0)?.sunrise.toString())
    Button(onClick = { navController.navigate("main_screen") }) {
        Text("WeatherScreen")
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
                    text =("${weatherData.value?.weatherCond?.average?.toInt().toString()}°") ?: "65°",
                    fontSize = 100.sp
                )
                // Image(painter = painterResource(id = R.drawable.sunshine),
                //   contentDescription = "My Image",
                //    alignment = Alignment.Center,
                //   contentScale = ContentScale.FillBounds,
                //   modifier = Modifier.size(150.dp)

            }
            Text("Feels Like: ${weatherData.value?.weatherCond?.feel?.toInt().toString()}°"
                ?: "Feels Like: 67°")
        }
        Text("Low: ${weatherData.value?.weatherCond?.low?.toInt().toString()} °"
            ?: "Low : 23°")
        Text("High: ${weatherData.value?.weatherCond?.high?.toInt().toString()}°"
            ?: "High : 76°"
        )
        Text("Humidity: ${weatherData.value?.weatherCond?.humidity.toString()}%"
            ?: "Humidity: 67%")
        Text("Pressure: ${weatherData.value?.weatherCond?.pressure.toString()} mPa"
            ?: "Pressure: 1023 mPa")
        Button(onClick = { navController.navigate("forecast_screen") }) {
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
