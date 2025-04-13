package app.recruit.jetweatherforecast.network

import app.recruit.jetweatherforecast.model.Weather
import app.recruit.jetweatherforecast.model.WeatherItem
import app.recruit.jetweatherforecast.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

//The `WeatherApi` interface serves as the network layer definition for fetching weather data in this application. Here's its function within the project context:
//
//1. **API Interface**: It defines how the app communicates with a weather service API (likely OpenWeatherMap based on the endpoint structure).
//
//2. **Retrofit Implementation**: Uses Retrofit annotations (`@GET`, `@Query`) to construct API requests. The interface method will be implemented automatically by Retrofit.
//
//3. **Data Retrieval**: The `getWeather()` suspend function makes an asynchronous GET request to fetch weather data for a specific city.
//
//4. **Parameter Handling**:
//   - Takes a city name as query
//   - Uses imperial units by default
//   - Includes the API key from Constants
//
//5. **Response Mapping**: Returns data mapped to the `WeatherItem` model class, which contains all weather information including the nested `WeatherObject` list.
//
//6. **Dependency Injection**: The `@Singleton` annotation suggests this interface is meant to be injected as a single instance throughout the app.
//
//This interface would typically be used by the `WeatherRepository` class (currently empty), which would abstract the network operations and provide weather data to the rest of the application.

@Singleton
interface WeatherApi {

    @GET(value = "data/2.5/forecast/daily")

    suspend  fun getWeather(
        @Query("q") query : String,
        @Query("units") units: String = "imperial",
        @Query("appid") appId: String = Constants.API_KEY
    ) : Weather
}