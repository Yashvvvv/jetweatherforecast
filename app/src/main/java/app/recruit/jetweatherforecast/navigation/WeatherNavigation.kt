package app.recruit.jetweatherforecast.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import app.recruit.jetweatherforecast.screens.main.MainScreen
import app.recruit.jetweatherforecast.screens.main.MainViewModel
import app.recruit.jetweatherforecast.screens.splash.WeatherSplashScreen
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.navArgument
import app.recruit.jetweatherforecast.screens.about.AboutScreen
import app.recruit.jetweatherforecast.screens.favourites.FavouriteScreen
import app.recruit.jetweatherforecast.screens.search.SearchScreen
import app.recruit.jetweatherforecast.screens.settings.SettingsScreen


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

        //www.google.com/cityname = "Seatle"
        val route = WeatherScreens.MainScreen.name

        composable("$route/{city}",
            arguments = listOf(
                navArgument(name = "city")
                {
                    type = NavType.StringType
                }
            )
        ){ navBack ->
            navBack.arguments?.getString("city").let{ city ->

                val mainViewModel = hiltViewModel<MainViewModel>()
                MainScreen(navController = navController, mainViewModel, city =city)
            }
        }

        composable(WeatherScreens.SearchScreen.name) {
            SearchScreen(navController = navController)
        }

        composable(WeatherScreens.FavouriteScreen.name) {
            FavouriteScreen(navController = navController)
        }

        composable(WeatherScreens.AboutScreen.name) {
            AboutScreen(navController = navController)
        }

        composable(WeatherScreens.SettingsScreen.name) {
            SettingsScreen(navController = navController)
        }
    }
}
