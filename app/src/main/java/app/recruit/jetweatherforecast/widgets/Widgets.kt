package app.recruit.jetweatherforecast.widgets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import app.recruit.jettnote.R
import app.recruit.jetweatherforecast.model.WeatherItem
import app.recruit.jetweatherforecast.utils.formatDate
import app.recruit.jetweatherforecast.utils.formatDateTime
import app.recruit.jetweatherforecast.utils.formatDecimal
import coil3.compose.AsyncImage

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
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Text(formatDate(data.dt)
                .split(",")[0],
                modifier = Modifier.padding(start = 5.dp)
            )

            WeatherStateImage(imageUrl = imageUrl)

            Surface(modifier  = Modifier.padding(0.dp),
                shape = CircleShape,
                color = Color(0xFFB1D8FA),
            ){
                Text(data.weather[0].description,
                    modifier = Modifier.padding(4.dp),
                    style = androidx.compose.material3.MaterialTheme.typography.bodyLarge,
                    fontWeight = androidx.compose.ui.text.font.FontWeight.SemiBold,
                    color = Color.Black.copy(alpha = 0.9f),
                )
            }

            Text(text = buildAnnotatedString {

                withStyle(style = SpanStyle(color = Color.Black.copy(alpha = 0.9f),
                    fontWeight = FontWeight.SemiBold,)
                ) {
                    append(" ${formatDecimal(data.temp.min)}°")
                }
                withStyle(style = SpanStyle(color = Color.Black.copy(alpha = 0.7f),
                    fontWeight = FontWeight.SemiBold,)
                ) {
                    append(" ${formatDecimal(data.temp.max)}°")
                }

            })

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
                text = formatDateTime(weather.sunrise),
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
                text = formatDateTime(weather.sunset),
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
                text = "${weather.humidity}%",
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
                text = "${weather.pressure} hPa",
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
                text = "${weather.speed} km/h",
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