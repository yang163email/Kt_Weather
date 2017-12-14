package com.yan.weather.domain.commands

import com.yan.weather.domain.datasource.ForecastProvider
import com.yan.weather.domain.model.ForecastList

/**
 *  @author      : 楠GG
 *  @date        : 2017/12/13 11:34
 *  @description : TODO
 */
class RequestForecastCommand(
        private val zipCode: Long,
        private val forecastProvider: ForecastProvider = ForecastProvider()) :
        Command<ForecastList> {

    companion object {
        val DAYS = 7
    }

    override fun execute() = forecastProvider.requestByZipCode(zipCode, DAYS)
}