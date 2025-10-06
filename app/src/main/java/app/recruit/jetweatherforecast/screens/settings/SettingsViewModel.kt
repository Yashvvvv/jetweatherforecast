package app.recruit.jetweatherforecast.screens.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.recruit.jetweatherforecast.repository.SettingsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val settingsRepository: SettingsRepository
) : ViewModel() {

    private val _selectedUnit = MutableStateFlow(settingsRepository.getTemperatureUnitDisplay())
    val selectedUnit: StateFlow<String> = _selectedUnit

    private val _unitChanged = MutableStateFlow(false)
    val unitChanged: StateFlow<Boolean> = _unitChanged

    fun updateTemperatureUnit(displayUnit: String) {
        viewModelScope.launch {
            _selectedUnit.value = displayUnit

            // Convert display unit to API unit format
            val apiUnit = when (displayUnit) {
                "Imperial (°F)" -> SettingsRepository.UNIT_IMPERIAL
                "Standard (K)" -> SettingsRepository.UNIT_STANDARD
                else -> SettingsRepository.UNIT_METRIC
            }

            settingsRepository.saveTemperatureUnit(apiUnit)
            _unitChanged.value = true
        }
    }

    fun resetUnitChangedFlag() {
        _unitChanged.value = false
    }
}
