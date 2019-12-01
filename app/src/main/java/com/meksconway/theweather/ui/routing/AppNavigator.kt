package com.meksconway.theweather.ui.routing

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.meksconway.theweather.ui.home.MainActivity
import com.meksconway.theweather.util.ScreenType

class AppNavigator(private val activity: AppCompatActivity) : Navigator {


    companion object {
        const val SCREEN_TYPE = "screen_type"
    }

    //TODO()
    override fun showWeatherActivity() {
        navigateTo(getIntent<MainActivity>().apply {
            putExtra(SCREEN_TYPE, ScreenType.WEATHER)
        })
    }

    private inline fun <reified T : Activity> getIntent() = Intent(activity, T::class.java)

    private fun navigateTo(intent: Intent) = activity.startActivity(intent)
}
