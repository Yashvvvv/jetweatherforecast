# 🌤️ Jet Weather Forecast

A modern, feature-rich Android weather application built with Jetpack Compose and Material Design 3, demonstrating clean architecture and professional UI/UX design.

## 📱 Features

### Core Functionality
- **Real-time Weather Data**: Fetches current weather and 7-day forecasts using OpenWeatherMap API
- **City Search**: Search and view weather for any city worldwide
- **Favorites Management**: Save and quickly access your favorite locations
- **Manual Refresh**: Pull-to-refresh functionality to update weather data
- **Error Handling**: Robust error handling with user-friendly error messages and retry functionality

### User Experience
- **Animated Splash Screen**: Professional splash screen with smooth animations and gradient backgrounds
- **Loading States**: Clear loading indicators for better user feedback
- **Responsive UI**: Adaptive layouts that work seamlessly across different screen sizes
- **Modern Design**: Material Design 3 with custom color schemes and gradient effects

### Technical Features
- **Offline Support**: Room database for caching weather data
- **Dependency Injection**: Dagger Hilt for clean dependency management
- **MVVM Architecture**: Clean separation of concerns with ViewModel pattern
- **Reactive Programming**: Kotlin Coroutines and Flow for asynchronous operations
- **Image Loading**: Coil for efficient weather icon loading
- **Type-safe Navigation**: Jetpack Navigation Compose for screen transitions

## 🏗️ Architecture

This app follows the **MVVM (Model-View-ViewModel)** architecture pattern with clean architecture principles:

```
app/
├── data/              # Data layer
│   └── DataOrException.kt
├── di/                # Dependency injection modules
├── model/             # Data models
├── network/           # API service interfaces
├── repository/        # Data repository layer
├── screens/           # UI screens (View layer)
│   ├── main/
│   ├── splash/
│   ├── search/
│   ├── favourites/
│   ├── settings/
│   └── about/
├── navigation/        # Navigation configuration
├── widgets/           # Reusable UI components
├── ui/theme/          # Material Design theme
└── utils/             # Utility functions
```

## 🛠️ Tech Stack

### Android & Kotlin
- **Kotlin** - Modern programming language for Android
- **Jetpack Compose** - Modern declarative UI framework
- **Material Design 3** - Latest Material Design guidelines

### Architecture Components
- **ViewModel** - UI-related data holder, lifecycle aware
- **Room Database** - Local data persistence
- **Navigation Component** - Fragment/screen navigation
- **Hilt** - Dependency injection framework

### Networking & Data
- **Retrofit** - Type-safe HTTP client
- **OkHttp** - HTTP client for network requests
- **Gson** - JSON serialization/deserialization
- **Kotlin Coroutines** - Asynchronous programming
- **Flow** - Reactive data streams

### UI & Images
- **Coil** - Image loading library optimized for Compose
- **Accompanist** - Supplementary Compose libraries
- **Custom Gradients** - Beautiful gradient backgrounds

### Build & Tools
- **KSP (Kotlin Symbol Processing)** - Fast annotation processing
- **Gradle Kotlin DSL** - Modern build configuration

## 🎨 UI Highlights

- **Gradient Backgrounds**: Beautiful vertical and radial gradients
- **Circular Weather Display**: Eye-catching circular card with weather information
- **Smooth Animations**: Bounce and fade animations for splash screen
- **Custom Color Scheme**: Professional blue-themed color palette
- **Material 3 Components**: Latest Material Design components
- **Weather Icons**: Dynamic weather icons from OpenWeatherMap
- **Responsive Cards**: Elevated cards with shadows and rounded corners

## 📋 Prerequisites

- Android Studio Hedgehog | 2023.1.1 or newer
- JDK 11 or higher
- Android SDK 33 or higher
- OpenWeatherMap API key

## 🚀 Getting Started

### 1. Clone the Repository
```bash
git clone https://github.com/yourusername/JetWeatherForecast.git
cd JetWeatherForecast
```

### 2. Get API Key
1. Visit [OpenWeatherMap](https://openweathermap.org/api)
2. Sign up for a free account
3. Generate an API key

### 3. Configure API Key
Create a `local.properties` file in the root directory:
```properties
WEATHER_API_KEY=your_api_key_here
sdk.dir=C:\\Users\\YourUsername\\AppData\\Local\\Android\\Sdk
```

### 4. Build and Run
```bash
./gradlew assembleDebug
./gradlew installDebug
```

Or simply click **Run** in Android Studio.

## 📦 Project Structure

### Key Components

#### ViewModel Layer
```kotlin
@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: WeatherRepository
) : ViewModel() {
    // Manages UI state and business logic
    suspend fun getWeather(city: String): DataOrException<Weather, Boolean, Exception>
    fun refreshWeather(city: String)
}
```

#### Repository Pattern
```kotlin
class WeatherRepository @Inject constructor(
    private val api: WeatherApi
) {
    suspend fun getWeather(cityQuery: String, units: String): 
        DataOrException<Weather, Boolean, Exception>
}
```

#### Composable UI
```kotlin
@Composable
fun MainScreen(navController: NavController, mainViewModel: MainViewModel, city: String?)
@Composable
fun WeatherSplashScreen(navController: NavController)
```

## 🎯 Key Features Demonstrated

### For Resume/Portfolio

1. **Clean Architecture**: Proper separation of concerns with layers
2. **Modern Android Development**: Latest Jetpack libraries and best practices
3. **Dependency Injection**: Proper DI setup with Hilt
4. **Reactive Programming**: Coroutines and Flow for async operations
5. **Error Handling**: Comprehensive error handling and user feedback
6. **Material Design**: Custom theme implementation following MD3 guidelines
7. **API Integration**: RESTful API consumption with Retrofit
8. **Local Storage**: Room database for offline capability
9. **Navigation**: Type-safe navigation with Compose
10. **State Management**: Proper state handling in Compose

## 🔧 Configuration

### API Configuration
- Base URL: `https://api.openweathermap.org/data/2.5/`
- Units: Metric (Celsius)
- Language: English

### Build Configuration
- Min SDK: 33 (Android 13)
- Target SDK: 35 (Android 15)
- Compile SDK: 35
- Java Version: 11

## 📸 Screenshots

*(Add screenshots of your app here)*

## 🧪 Testing

```bash
# Run unit tests
./gradlew test

# Run instrumentation tests
./gradlew connectedAndroidTest
```

## 📈 Future Enhancements

- [ ] Hourly weather forecast
- [ ] Weather maps integration
- [ ] Push notifications for weather alerts
- [ ] Widget support for home screen
- [ ] Dark mode support
- [ ] Multiple language support
- [ ] Weather comparison between cities
- [ ] Historical weather data

## 👨‍💻 Development Practices

- **Version Control**: Git with meaningful commit messages
- **Code Style**: Kotlin coding conventions
- **Documentation**: Inline comments and KDoc where necessary
- **Modular Design**: Reusable components and utilities
- **Error Handling**: Try-catch blocks and proper exception handling
- **Resource Management**: Proper lifecycle handling

## 📄 License

This project is available for portfolio and educational purposes.

## 🤝 Contributing

Contributions, issues, and feature requests are welcome!

## 📧 Contact

**Your Name**
- Email: your.email@example.com
- LinkedIn: [Your LinkedIn](https://linkedin.com/in/yourprofile)
- GitHub: [@yourusername](https://github.com/yourusername)

---

**Built with ❤️ using Jetpack Compose**
