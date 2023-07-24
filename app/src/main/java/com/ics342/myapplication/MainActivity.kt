package com.ics342.myapplication

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.ics342.myapplication.screens.DetailsScreen
import com.ics342.myapplication.screens.HomeScreen
import com.ics342.myapplication.ViewModels.ForecastViewModel
import com.ics342.myapplication.ViewModels.WeatherViewModel
import com.ics342.myapplication.ui.theme.MyApplicationTheme
import dagger.hilt.android.AndroidEntryPoint

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
        val currentDestination = navController.currentBackStackEntry?.destination?.route

    LaunchedEffect(Unit) {
            weatherViewModel.viewAppeared()
            forecastViewModel.viewAppeared()
        }

    Scaffold(
            topBar = {
                when (currentDestination) {
                    Screens.Home.route -> {
                        TopBarLayout("Weather App") }
                    Screens.Details.route -> {
                        TopBarLayout("Forecast") }
                }
            },
            content = {
                NavHost(navController, startDestination = Screens.Home.route) {
                    composable(Screens.Home.route) { HomeScreen(navController, weatherData) }
                    composable(Screens.Details.route) { DetailsScreen(navController,forecastData) }
                }
            }
        )



    /*

    */
    }

@Composable
fun WeatherConditionIcon(url: String) {
    AsyncImage(model = url, contentDescription = "")
}
@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun PreviewApp(){
    MyApp()
}