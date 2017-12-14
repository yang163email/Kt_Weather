package com.yan.weather.net

import com.google.gson.Gson
import com.yan.weather.module.ForecastResult
import java.net.URL

/**
 *  @author      : 楠GG
 *  @date        : 2017/12/13 11:06
 *  @description : TODO
 */
class ForecastRequest(val zipCode: Long) {
    companion object {
        private val APP_ID = "15646a06818f61f7b8d7823ca833e1ce"
        private val URL = "http://api.openweathermap.org/data/2.5/forecast/daily?mode=json&units=metric&cnt=7"
        private val COMPLETE_URL = "$URL&APPID=$APP_ID&zip="
    }

    fun execute(): ForecastResult {
        val forecastJsonStr = URL(COMPLETE_URL + zipCode).readText()
        return Gson().fromJson(forecastJsonStr, ForecastResult::class.java)
    }
}