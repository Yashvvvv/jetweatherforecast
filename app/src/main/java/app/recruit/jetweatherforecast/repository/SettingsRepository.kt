package app.recruit.jetweatherforecast.repository

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SettingsRepository @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("weather_settings", Context.MODE_PRIVATE)

    companion object {
        private const val KEY_TEMPERATURE_UNIT = "temperature_unit"
        const val UNIT_METRIC = "metric"
        const val UNIT_IMPERIAL = "imperial"
        const val UNIT_STANDARD = "standard"
    }

    fun saveTemperatureUnit(unit: String) {
        sharedPreferences.edit {
            putString(KEY_TEMPERATURE_UNIT, unit)
        }
    }

    fun getTemperatureUnit(): String {
        return sharedPreferences.getString(KEY_TEMPERATURE_UNIT, UNIT_METRIC) ?: UNIT_METRIC
    }

    fun getTemperatureUnitDisplay(): String {
        return when (getTemperatureUnit()) {
            UNIT_IMPERIAL -> "Imperial (°F)"
            UNIT_STANDARD -> "Standard (K)"
            else -> "Metric (°C)"
        }
    }
}
