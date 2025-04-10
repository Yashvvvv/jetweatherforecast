package app.recruit.jetweatherforecast.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import app.recruit.jetweatherforecast.screens.main.MainScreen
import app.recruit.jetweatherforecast.screens.main.MainViewModel
import app.recruit.jetweatherforecast.screens.splash.WeatherSplashScreen
import androidx.hilt.navigation.compose.hiltViewModel


//Will store the NavController

@Composable
fun WeatherNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController,
        startDestination = WeatherScreens.SplashScreen.name) {
        //Here we will add all the screens
        //We will use the name of the screen as the route
        composable(WeatherScreens.SplashScreen.name) {
            WeatherSplashScreen(navController = navController)
        }

        composable(WeatherScreens.MainScreen.name) {
            val mainViewModel = hiltViewModel<MainViewModel>()
            MainScreen(navController = navController, mainViewModel)
        }
    }
}