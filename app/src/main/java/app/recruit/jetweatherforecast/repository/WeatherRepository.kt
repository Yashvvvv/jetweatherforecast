package app.recruit.jetweatherforecast.repository

import app.recruit.jetweatherforecast.data.DataOrException
import app.recruit.jetweatherforecast.model.WeatherItem
import app.recruit.jetweatherforecast.network.WeatherApi
import javax.inject.Inject

class WeatherRepository  @Inject constructor(private val api: WeatherApi){ //Get the data from the weatherApi using retrofit
    suspend fun getWeather(cityQuery: String):
            DataOrException<WeatherItem, Boolean, Exception> {

        val response = try {
            api.getWeather(query = cityQuery)

        } catch (e: Exception) {
            return DataOrException(e = e)
        }
        return DataOrException(data = response) //return the data
    }
}