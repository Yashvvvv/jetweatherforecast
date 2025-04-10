package app.recruit.jetweatherforecast.screens.main

import app.recruit.jettnote.R
import android.R.attr.fontWeight
import android.R.attr.tint
import android.R.style
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
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
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import app.recruit.jetweatherforecast.data.DataOrException
import app.recruit.jetweatherforecast.model.WeatherItem
import app.recruit.jetweatherforecast.utils.formatDate
import app.recruit.jetweatherforecast.utils.formatDateTime
import app.recruit.jetweatherforecast.utils.formatDecimal
import app.recruit.jetweatherforecast.widgets.WeatherAppBar
import coil3.compose.AsyncImage

@Composable
fun MainScreen(
    navController: NavController,
    mainViewModel: MainViewModel = hiltViewModel()) {

    val weatherData = produceState<DataOrException<WeatherItem, Boolean, Exception>>(
    initialValue = DataOrException(loading = true)) {

        value = mainViewModel.getWeather(city = "Lisbon")

    }.value

    if (weatherData.loading ==true){
        CircularProgressIndicator()
    }else if (weatherData.data != null){
        MainScaffold(weather = weatherData.data!!, navController)
    }
}

@Composable
fun MainScaffold(weather: WeatherItem, navController: NavController) {
    Scaffold(
        topBar = {
            WeatherAppBar(
                title = weather.city.name + " ${weather.city.country}",
                elevation = 10.dp,
                navController = navController
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
fun MainContent(data: WeatherItem) {

    val imageUrl = "https://openweathermap.org/img/wn/${data.weather[0].icon}.png"

    Column(
        modifier = Modifier.padding(4.dp)
        .fillMaxWidth(),
        verticalArrangement = androidx.compose.foundation.layout.Arrangement.Center,
        horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally) {

        Text(text = formatDate(data.dt),
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
                    text = formatDecimal(data.list.temp) + "°",
                    style = androidx.compose.material3.MaterialTheme.typography.headlineLarge,
                    fontWeight = androidx.compose.ui.text.font.FontWeight.ExtraBold,
                    color = Color.White,
                    fontSize = 40.sp // Smaller font size
                )

                Text(
                    text = data.weather[0].main,
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
                        text = "${data.main.humidity}%",
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
    HumidityWindPressureRow(weather = data)
    HorizontalDivider(modifier = Modifier.padding(1.dp))
    SunsetSunriseRow(weather = data)
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
            item { WeatherDetailsRow(data = data) }
        }
    }
}

@Composable
fun WeatherDetailsRow(data: WeatherItem) {

    val imageUrl = "https://openweathermap.org/img/wn/${data.weather[0].icon}.png"

    Surface(modifier = Modifier
        .padding(3.dp)
        .fillMaxWidth(),
        shape = CircleShape.copy(topEnd = CornerSize(6.dp)),
        color = Color.White
    ){
        Row(modifier = Modifier
            .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween) {

            Text(formatDate(data.main.temp_max.toInt())
                .split(",")[0],
                modifier = Modifier.padding(start = 5.dp))

            WeatherStateImage(imageUrl = imageUrl)
        }
    }
}

@Composable
fun SunsetSunriseRow(weather: WeatherItem) {
    Row(modifier = Modifier
        .padding(12.dp)
        .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(modifier = Modifier.padding(4.dp)) {
            Icon(
                painter = painterResource(id = R.drawable.sunrise),
                contentDescription = "Sunrise",
                modifier = Modifier.size(20.dp),
                tint = Color.Black
            )
            Text(
                text = formatDateTime(weather.sys.sunrise),
                style = androidx.compose.material3.MaterialTheme.typography.bodyLarge,
                fontWeight = androidx.compose.ui.text.font.FontWeight.SemiBold,
                color = Color.Black.copy(alpha = 0.9f),
            )
        }

        Row {
            Icon(
                painter = painterResource(id = R.drawable.sunset),
                contentDescription = "Sunset",
                modifier = Modifier.size(20.dp),
                tint = Color.Black
            )
            Text(
                text = formatDateTime(weather.sys.sunset),
                style = androidx.compose.material3.MaterialTheme.typography.bodyLarge,
                fontWeight = androidx.compose.ui.text.font.FontWeight.SemiBold,
                color = Color.Black.copy(alpha = 0.9f),
            )
        }
    }
}

@Composable
fun HumidityWindPressureRow(weather: WeatherItem){
    Row(modifier = Modifier
        .padding(12.dp)
        .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(modifier = Modifier.padding(4.dp)) {
            Icon(
                painter = painterResource(id = R.drawable.humidity),
                contentDescription = "Humidity",
                modifier = Modifier.size(20.dp),
                tint = Color.Black
            )
            Text(
                text = "${weather.main.humidity}%",
                style = androidx.compose.material3.MaterialTheme.typography.bodyLarge,
                fontWeight = androidx.compose.ui.text.font.FontWeight.SemiBold,
                color = Color.Black.copy(alpha = 0.9f),
            )
        }

        Row {
            Icon(
                painter = painterResource(id = R.drawable.blood_pressure),
                contentDescription = "BP",
                modifier = Modifier.size(20.dp),
                tint = Color.Black
            )
            Text(
                text = "${weather.main.pressure} hPa",
                style = androidx.compose.material3.MaterialTheme.typography.bodyLarge,
                fontWeight = androidx.compose.ui.text.font.FontWeight.SemiBold,
                color = Color.Black.copy(alpha = 0.9f),
            )
        }

        Row {
            Icon(
                painter = painterResource(id = R.drawable.wind),
                contentDescription = "Wind",
                modifier = Modifier.size(20.dp),
                tint = Color.Black
            )
            Text(
                text = "${weather.wind.speed} km/h",
                style = androidx.compose.material3.MaterialTheme.typography.bodyLarge,
                fontWeight = androidx.compose.ui.text.font.FontWeight.SemiBold,
                color = Color.Black.copy(alpha = 0.9f),
            )
        }
    }
}

@Composable
fun WeatherStateImage(imageUrl: String) {
    AsyncImage(
        model = imageUrl,
        contentDescription = "Weather Icon",
        modifier = Modifier.size(80.dp)
    )}