package com.yan.weather.domain.model

/**
 *  @author      : 楠GG
 *  @date        : 2017/12/13 11:13
 *  @description : TODO
 */
data class ForecastList(
        val city: String,
        val country: String,
        val dailyForecast:List<Forecast>
)

data class Forecast(
        val date: String,
        val description: String,
        val high: Int,
        val low: Int
)