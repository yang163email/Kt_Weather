package com.yan.weather.module.server

import com.yan.weather.domain.datasource.ForecastDataSource
import com.yan.weather.domain.model.ForecastList
import com.yan.weather.module.db.ForecastDB

/**
 *  @author      : 楠GG
 *  @date        : 2017/12/14 12:32
 *  @description : TODO
 */
class ForecastServer(private val dataMapper: ServerDataMapper = ServerDataMapper(),
                     private val forecastDb: ForecastDB = ForecastDB()) : ForecastDataSource {

    override fun requestForecastByZipCode(zipCode: Long, date: Long): ForecastList? {
        val result = ForecastByZipCodeRequest(zipCode).execute()
        val converted = dataMapper.convertToDomain(zipCode, result)
        forecastDb.saveForecast(converted)
        return forecastDb.requestForecastByZipCode(zipCode, date)
    }

    override fun requestDayForecast(id: Long) = throw UnsupportedOperationException()
}