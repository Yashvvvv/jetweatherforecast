package app.recruit.jetweatherforecast.screens.main

import android.util.Log
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import app.recruit.jetweatherforecast.data.DataOrException
import app.recruit.jetweatherforecast.model.Weather
import app.recruit.jetweatherforecast.navigation.WeatherScreens
import app.recruit.jetweatherforecast.utils.formatDate
import app.recruit.jetweatherforecast.utils.formatDecimal
import app.recruit.jetweatherforecast.widgets.HumidityWindPressureRow
import app.recruit.jetweatherforecast.widgets.SunsetSunriseRow
import app.recruit.jetweatherforecast.widgets.WeatherAppBar
import app.recruit.jetweatherforecast.widgets.WeatherDetailsRow
import coil3.compose.AsyncImage

@Composable
fun MainScreen(
    navController: NavController,
    mainViewModel: MainViewModel = hiltViewModel(),
    city: String?
) {

    val weatherData = produceState<DataOrException<Weather, Boolean, Exception>>(
    initialValue = DataOrException(loading = true)) {

        value = mainViewModel.getWeather(city = city.toString())

    }.value

    if (weatherData.loading ==true){
        CircularProgressIndicator()
    }else if (weatherData.data != null){
        MainScaffold(weather = weatherData.data!!, navController)
    }
}

@Composable
fun MainScaffold(weather: Weather, navController: NavController) {
    Scaffold(
        topBar = {
            WeatherAppBar(
                title = weather.city.name + " ,${weather.city.country}",
                elevation = 10.dp,
                navController = navController,
                onAddActionClicked = {
                    navController.navigate(WeatherScreens.SearchScreen.name)
                }
            ) {
                Log.d("TAG", "MainScaffold: Button Clicked")
            }
        }
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            MainContent(data = weather)
        }
    }
}

@Composable
fun MainContent(data: Weather) {

    val weatherItem = data.list[0]
    val imageUrl = "https://openweathermap.org/img/wn/${weatherItem.weather[0].icon}.png"

    Column(
        modifier = Modifier.padding(4.dp)
        .fillMaxWidth(),
        verticalArrangement = androidx.compose.foundation.layout.Arrangement.Center,
        horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally) {

        Text(text = formatDate(weatherItem.dt),
            style = androidx.compose.material3.MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(6.dp),
            color = androidx.compose.material3.MaterialTheme.colorScheme.onSecondaryContainer
        )

        Surface(
            modifier = Modifier
                .padding(4.dp)
                .size(250.dp)
                .padding(4.dp),
            shape = CircleShape,
            shadowElevation = 20.dp,
            color = Color.Transparent
        ) {
            // Outer gradient background
            androidx.compose.foundation.Canvas(modifier = Modifier.fillMaxSize()) {
                val outerGradient = androidx.compose.ui.graphics.Brush.radialGradient(
                    colors = listOf(
                        Color(0xFF42A5F5),  // Lighter blue
                        Color(0xFF1976D2),  // Medium blue
                        Color(0xFF0D47A1)   // Dark blue
                    ),
                    center = androidx.compose.ui.geometry.Offset(size.width / 2, size.height / 2),
                    radius = size.width / 1.2f
                )
                drawCircle(brush = outerGradient)
            }

            // Content with inner glow effect
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp), // Reduced padding for smaller surface
                verticalArrangement = androidx.compose.foundation.layout.Arrangement.Center,
                horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
            ) {
                // Weather icon with enhanced appearance
                AsyncImage(
                    model = imageUrl,
                    contentDescription = "Weather Icon",
                    modifier = Modifier
                        .size(50.dp) // Reduced icon size
                        .padding(bottom = 2.dp)
                )

                Text(
                    text = formatDecimal(weatherItem.temp.day) + "°",
                    style = androidx.compose.material3.MaterialTheme.typography.headlineLarge,
                    fontWeight = androidx.compose.ui.text.font.FontWeight.ExtraBold,
                    color = Color.White,
                    fontSize = 40.sp // Smaller font size
                )

                Text(
                    text = weatherItem.weather[0].main,
                    style = androidx.compose.material3.MaterialTheme.typography.titleMedium,
                    fontWeight = androidx.compose.ui.text.font.FontWeight.Medium,
                    color = Color.White.copy(alpha = 0.95f),
                    fontSize = 18.sp, // Smaller font size
                    modifier = Modifier.padding(top = 2.dp)
                )

                androidx.compose.foundation.layout.Spacer(modifier = Modifier.height(6.dp))

                // Add humidity or another weather metric with subtle styling
                androidx.compose.foundation.layout.Row(
                    verticalAlignment = androidx.compose.ui.Alignment.CenterVertically,
                    modifier = Modifier.padding(top = 4.dp) // Reduced padding
                ) {
                    Text(
                        text = "${weatherItem.humidity}%",
                        style = androidx.compose.material3.MaterialTheme.typography.bodyLarge,
                        fontWeight = androidx.compose.ui.text.font.FontWeight.SemiBold,
                        color = Color.White.copy(alpha = 0.9f),
                        fontSize = 14.sp // Slightly smaller font size
                    )
                    Text(
                        text = " humidity",
                        style = androidx.compose.material3.MaterialTheme.typography.bodyMedium,
                        color = Color.White.copy(alpha = 0.7f),
                        fontSize = 14.sp // Slightly smaller font size
                    )
                }
            }
        }
    }

    Spacer(modifier = Modifier.height(10.dp))
    HumidityWindPressureRow(weather = data.list[0])
    HorizontalDivider(modifier = Modifier.padding(1.dp))
    SunsetSunriseRow(weather = data.list[0])
    Text(
        text = "Weather Details",
        style = androidx.compose.material3.MaterialTheme.typography.titleMedium,
        modifier = Modifier
            .fillMaxWidth()  // Makes the text component take full width
            .padding(6.dp),
        color = androidx.compose.material3.MaterialTheme.colorScheme.onSecondaryContainer,
        textAlign = TextAlign.Center  // This only aligns text within the component
    )

    Surface(modifier = Modifier.fillMaxWidth()
        .fillMaxHeight(),
        color = Color(0xFF6FA6D0),
        shape = RoundedCornerShape(size = 14.dp),
        shadowElevation = 20.dp
    ) {
        LazyColumn(modifier = Modifier.padding(2.dp),
            contentPadding = PaddingValues(1.dp)
        ) {
            items(items = data.list){
                WeatherDetailsRow(data = it)
            }}
        }
    }