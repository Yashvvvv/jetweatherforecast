package app.recruit.jetweatherforecast

import android.app.Application
import dagger.hilt.android.HiltAndroidApp


//So this ties everything together to say, okay, we're creating this weather application class which
//implements or in this case inherits from application the Android application class, the class that
//knows everything about all the assets and everything in our project.
//We're also saying that we are going to make sure that this is not just any weather application class
//here, it's also a hilt Android app so that in the background hilt will know what to do to set everything
//up to make this application able to use the dependency injections and so forth.
//Okay.
//And that's all that we need to do for our weather application.

@HiltAndroidApp
class WeatherApplication: Application() {

}