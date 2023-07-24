sealed class Screens(val route: String){
    object Home: Screens(route="main_screen")
    object Details: Screens(route="forecast_screen")

}