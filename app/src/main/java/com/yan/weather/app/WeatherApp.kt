package com.yan.weather.app

import android.app.Application
import com.yan.weather.utils.DelegatesExt

/**
 *  @author      : 楠GG
 *  @date        : 2017/12/13 17:17
 *  @description : TODO
 */
class WeatherApp : Application() {

    companion object {
        var instance: WeatherApp by DelegatesExt.notNullSingleValue()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}