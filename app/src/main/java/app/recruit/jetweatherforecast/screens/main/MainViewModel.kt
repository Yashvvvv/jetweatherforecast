package app.recruit.jetweatherforecast.screens.main

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.recruit.jetweatherforecast.data.DataOrException
import app.recruit.jetweatherforecast.model.Weather
import app.recruit.jetweatherforecast.repository.WeatherRepository
import app.recruit.jetweatherforecast.repository.SettingsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: WeatherRepository,
    private val settingsRepository: SettingsRepository
) : ViewModel() {

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean> = _isRefreshing.asStateFlow()

    val data: MutableState<DataOrException<Weather, Boolean, Exception>> =
        mutableStateOf(DataOrException(null, true, Exception("")))

    suspend fun getWeather(city: String, units: String = settingsRepository.getTemperatureUnit())
            : DataOrException<Weather, Boolean, Exception> {
        return repository.getWeather(cityQuery = city, units = units)
    }

    fun refreshWeather(city: String) {
        viewModelScope.launch {
            _isRefreshing.value = true
            try {
                val currentUnit = settingsRepository.getTemperatureUnit()
                data.value = getWeather(city, currentUnit)
            } catch (e: Exception) {
                Log.e("MainViewModel", "Error refreshing weather", e)
            } finally {
                _isRefreshing.value = false
            }
        }
    }
}
