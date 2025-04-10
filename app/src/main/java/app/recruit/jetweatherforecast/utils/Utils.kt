package app.recruit.jetweatherforecast.utils


import java.text.SimpleDateFormat
import java.util.Date

// Format timestamp to readable date format
fun formatDate(timestamp: Int): String {
    val date = Date(timestamp.toLong() * 1000)
    val format = SimpleDateFormat("EEE, MMM d")

    return format.format(date)
}

fun formatDateTime(timestamp: Int): String {
    val date = Date(timestamp.toLong() * 1000)
    val format = SimpleDateFormat("hh:mm a")

    return format.format(date)
}

fun formatDecimal(item: Double): String {
    return "%.0f".format(item)
}

// Convert Kelvin to Fahrenheit
fun kelvinToFahrenheit(temp: Double): Int {
    return ((temp - 273.15) * 9/5 + 32).toInt()
}

// Format the temperature with degree symbol
fun formatTemp(temp: Double): String {
    return "${kelvinToFahrenheit(temp)}°"
}