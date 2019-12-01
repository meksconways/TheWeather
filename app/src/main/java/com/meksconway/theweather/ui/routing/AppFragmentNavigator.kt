package com.meksconway.theweather.ui.routing

import androidx.fragment.app.FragmentActivity
import com.meksconway.theweather.util.showFragment

class AppFragmentNavigator(private val activity: FragmentActivity) : FragmentNavigator{



    override fun showWeatherDetailFragment() {
//        activity.showFragment(WeatherDetailsFragment.newInstance(), R.id.fragmentContainer, true)
    }


}