package com.yan.weather.domain.commands

import com.yan.weather.domain.datasource.ForecastProvider
import com.yan.weather.domain.model.Forecast

/**
 *  @author      : 楠GG
 *  @date        : 2017/12/14 16:10
 *  @description : TODO
 */
class RequestDayForecastCommand(
        val id: Long,
        private val forecastProvider: ForecastProvider = ForecastProvider()) :
        Command<Forecast> {

    override fun execute() = forecastProvider.requestForecast(id)
}