package app.recruit.jetweatherforecast.di

import app.recruit.jetweatherforecast.network.WeatherApi
import app.recruit.jetweatherforecast.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


//# AppModule.kt in a Jetpack Weather Forecast App
//
//## What is it?
//`AppModule.kt` is a Dagger Hilt dependency injection module that centralizes the creation and configuration of application-wide dependencies.
//
//## Implementation
//The module:
//- Uses `@Module` and `@InstallIn(SingletonComponent::class)` to define an application-level dependency module
//- Provides a singleton instance of `WeatherApi` through the `provideOpenWeatherApi()` method
//- Configures Retrofit with:
//  - Base URL from Constants
//  - Gson converter for JSON parsing
//  - Creates an implementation of the WeatherApi interface
//
//## Significance
//
//### Dependency Injection
//It implements the dependency injection pattern using Dagger Hilt, allowing components throughout the app to receive dependencies without knowing how to create them.
//
//### Network Layer Configuration
//The module centralizes Retrofit configuration for fetching weather data, which is critical for this weather forecast application.
//
//### Single Responsibility
//By extracting network setup into this module:
//- The code is more maintainable
//- Network configuration is done in one place
//- Other components can focus on their specific responsibilities
//
//### Testability
//Dependency injection makes the codebase more testable by allowing dependencies to be easily mocked during testing.
//
//### Singleton Management
//The `@Singleton` annotation ensures only one instance of `WeatherApi` exists throughout the app lifecycle, optimizing resource usage.
//
//This module acts as the foundation for the app's network operations, enabling components to request weather data through a properly configured API client.

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideOpenWeatherApi(): WeatherApi{ //Dagger-Hilt will provide the WeatherApi instance and they will call this function as a provider
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherApi::class.java)
    }
}