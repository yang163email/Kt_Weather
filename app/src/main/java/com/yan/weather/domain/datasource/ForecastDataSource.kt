package com.yan.weather.domain.datasource

import com.yan.weather.domain.model.Forecast
import com.yan.weather.domain.model.ForecastList


/**
 *  @author      : 楠GG
 *  @date        : 2017/12/14 11:43
 *  @description : TODO
 */
interface ForecastDataSource {
    fun requestForecastByZipCode(zipCode: Long, date: Long): ForecastList?

    fun requestDayForecast(id: Long): Forecast?
}