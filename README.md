# JetWeatherForecast

JetWeatherForecast is a modern Android weather application built with Jetpack Compose that provides daily weather forecasts. The app demonstrates modern Android development practices using Kotlin and the latest Android architecture components.

## Features

- Weather forecast data for multiple cities
- Daily weather forecasts with detailed information
- Search functionality to find weather for any city
- Save favorite locations for quick access
- Settings customization
- Clean and intuitive UI built with Jetpack Compose

## Tech Stack

- **Kotlin**: Primary programming language
- **Jetpack Compose**: Modern UI toolkit for building native Android UI
- **Material 3**: Design system for modern Android applications
- **MVVM Architecture**: Clean separation of UI, business logic, and data
- **Coroutines & Flow**: For asynchronous programming
- **Dagger Hilt**: For dependency injection
- **Room**: For local database storage
- **Retrofit & OkHttp**: For API communication
- **Navigation Compose**: For handling in-app navigation
- **Coil**: For image loading

## Project Structure

- **app**: Main application module
  - **data**: Contains data layer components
  - **di**: Dependency injection modules
  - **model**: Data models/entities for the application
  - **network**: API interfaces and network-related classes
  - **repository**: Data repositories
  - **screens**: UI screens of the application
    - **splash**: Splash screen
    - **main**: Main weather display
    - **search**: City search screen
    - **favourites**: Saved locations
    - **settings**: App settings
    - **about**: About screen
  - **ui**: Theme and UI-related components
  - **utils**: Utility classes and functions
  - **widgets**: Reusable compose UI components

## Installation

1. Clone this repository
2. Open the project in Android Studio (Arctic Fox or later)
3. Sync Gradle files
4. Run the app on an emulator or physical device

## API Key Setup

The app uses OpenWeatherMap API for weather data. To set up the API key securely:

1. Register at [OpenWeatherMap](https://openweathermap.org/) to get an API key
2. Copy the `local.properties.example` file to `local.properties` (this file is git-ignored)
3. Add your API key to the `local.properties` file:
   ```
   WEATHER_API_KEY=your_openweathermap_api_key_here
   ```
4. Sync the project - the API key will be automatically included in the app via BuildConfig

**Note**: Never commit your `local.properties` file to version control!

## Requirements

- Android Studio Arctic Fox or later
- Minimum SDK: 33 (Android 13)
- Target SDK: 35 (Android 15)
- Kotlin 1.9+

## Architecture

The app follows MVVM (Model-View-ViewModel) architecture pattern with clean architecture principles:

- **Model**: Data classes and repositories
- **View**: Jetpack Compose UI components and screens
- **ViewModel**: Manages UI-related data, handles user interactions

## Screenshots

[Include screenshots of the application here]

## Future Improvements

- Weather notifications
- Widgets for home screen
- More detailed hourly forecasts
- Weather maps
- Dark/Light theme toggle
- Weather alerts
- Multi-language support

## License

[Specify your license here]

## Acknowledgements

- Weather data provided by [OpenWeatherMap](https://openweathermap.org/)
- Icons and design resources 