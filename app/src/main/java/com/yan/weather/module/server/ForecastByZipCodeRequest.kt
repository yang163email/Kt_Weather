package com.yan.weather.module.server

import com.google.gson.Gson
import java.net.URL

/**
 *  @author      : 楠GG
 *  @date        : 2017/12/13 11:06
 *  @description : TODO
 */
class ForecastByZipCodeRequest(private val zipCode: Long, private val gson: Gson = Gson()) {

    companion object {
        private val APP_ID = "15646a06818f61f7b8d7823ca833e1ce"
        private val URL = "http://api.openweathermap.org/data/2.5/forecast/daily?mode=json&units=metric&cnt=7"
        private val COMPLETE_URL = "$URL&APPID=$APP_ID&zip="
    }

    fun execute(): ForecastResult {
        val url = COMPLETE_URL + zipCode
        val forecastJsonStr = URL(url).readText()
        return gson.fromJson(forecastJsonStr, ForecastResult::class.java)
    }
}