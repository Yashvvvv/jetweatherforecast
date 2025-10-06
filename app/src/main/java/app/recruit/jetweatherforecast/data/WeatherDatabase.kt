package app.recruit.jetweatherforecast.data

import androidx.room.Database
import androidx.room.RoomDatabase
import app.recruit.jetweatherforecast.model.Favorite
import app.recruit.jetweatherforecast.model.Unit

@Database(entities = [Favorite::class, Unit::class], version = 1, exportSchema = false)
abstract class WeatherDatabase : RoomDatabase() {
    abstract fun weatherDao(): WeatherDao
}

