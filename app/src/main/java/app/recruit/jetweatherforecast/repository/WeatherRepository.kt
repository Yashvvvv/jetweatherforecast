package app.recruit.jetweatherforecast.repository

import android.R
import app.recruit.jetweatherforecast.data.DataOrException
import app.recruit.jetweatherforecast.model.Weather
import app.recruit.jetweatherforecast.model.WeatherItem
import app.recruit.jetweatherforecast.network.WeatherApi
import javax.inject.Inject

class WeatherRepository @Inject constructor(private val api: WeatherApi) {

    suspend fun getWeather(cityQuery: String, units: String)
            : DataOrException<Weather, Boolean, Exception> {
        val response = try {
            api.getWeather(query = cityQuery, units = units)

        } catch (e: Exception) {
            return DataOrException(e = e)
        }
        return DataOrException(data = response)

    }
}