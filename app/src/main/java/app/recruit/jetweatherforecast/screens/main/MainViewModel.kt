package app.recruit.jetweatherforecast.screens.main

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.recruit.jetweatherforecast.data.DataOrException
import app.recruit.jetweatherforecast.model.WeatherItem
import app.recruit.jetweatherforecast.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel  @Inject constructor(private val repository: WeatherRepository)
    : ViewModel(){
    //The `MainViewModel` class is responsible for managing the UI-related data in a lifecycle-conscious way. It interacts with the `WeatherRepository` to fetch weather data and provides it to the UI.
        val data: MutableState<DataOrException<WeatherItem, Boolean, Exception>> =
        mutableStateOf(DataOrException(null, true, Exception("")))

    suspend fun getWeather(city: String)
    : DataOrException<WeatherItem, Boolean, Exception>{
        return repository.getWeather(cityQuery = city)
    }
}
//    init {
//        loadWeather()
//    }
//
//    private fun loadWeather() {
//        getWeather("London")
//    }
//
//    private fun getWeather(city: kotlin.String) {
//        viewModelScope.launch {
//            if(city.isEmpty()) return@launch
//            data.value.loading = true
//            data.value = repository.getWeather(cityQuery = city)
//
//            if(data.value.data.toString().isEmpty()) data.value.loading = false
//        }
//        Log.d("TAG", "getWeather: ${data.value.data.toString()}")
//    }
//}