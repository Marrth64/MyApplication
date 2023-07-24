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
import androidx.compose.ui.graphics.BlendMode.Companion.Screen
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.Coil
import coil.ImageLoader
import coil.compose.AsyncImage
import com.ics342.myapplication.ui.theme.MyApplicationTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
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
    viewModel: WeatherViewModel = hiltViewModel()
) {
        val navController = rememberNavController()
        val weatherData = viewModel.weatherData.observeAsState()
        LaunchedEffect(Unit) {
            viewModel.viewAppeared()
        }
        val currentDestination = navController.currentBackStackEntry?.destination?.route
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
                    composable("forecast_screen") { DetailsScreen(navController) }
                }
            }
        )



    /*

    */
    }

@Composable
fun DetailsScreen(navController: NavHostController) {
     Text("Hello World")
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
                Text(text =(weatherData.value?.weatherCond?.average?.let { kelvinToFahrenheit(it).toString() } + "°") ?: "°", fontSize = 100.sp)
                // Image(painter = painterResource(id = R.drawable.sunshine),
                //   contentDescription = "My Image",
                //    alignment = Alignment.Center,
                //   contentScale = ContentScale.FillBounds,
                //   modifier = Modifier.size(150.dp)

            }
            Text(text = ("Feels Like: "+weatherData.value?.weatherCond?.feel?.let { kelvinToFahrenheit(it).toString() } + "°")
                ?: "Feels Like: 67")
        }
        Text(text=("Low: "+weatherData.value?.weatherCond?.low?.let { kelvinToFahrenheit(it).toString() } + "°")
            ?: "Low : 23")
        Text(text= ("High: " + weatherData.value?.weatherCond?.high?.let { kelvinToFahrenheit(it).toString() } + "°")
            ?: "High : 76"
        )
        Text(text=("Humidity: "+weatherData.value?.weatherCond?.humidity.toString() )
            ?: "humidity: 67")
        Text(text=("Pressure: "+weatherData.value?.weatherCond?.pressure.toString() )
            ?: "pressure: 67")
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
