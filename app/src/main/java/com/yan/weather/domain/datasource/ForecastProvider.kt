package com.yan.weather.domain.datasource

import com.yan.weather.domain.model.Forecast
import com.yan.weather.domain.model.ForecastList
import com.yan.weather.ext.firstResult
import com.yan.weather.module.db.ForecastDB
import com.yan.weather.module.server.ForecastServer

/**
 *  @author      : 楠GG
 *  @date        : 2017/12/14 11:44
 *  @description : TODO
 */
class ForecastProvider(private val sources: List<ForecastDataSource> = SOURCES) {

    companion object {
        val DAY_IN_MILLIS = 1000 * 60 * 60 * 24
        val SOURCES by lazy { listOf(ForecastDB(), ForecastServer()) }
    }

    fun requestByZipCode(zipCode: Long, days: Int): ForecastList = requestToSources {
        val res = it.requestForecastByZipCode(zipCode, todayTimeSpan())
        if (res != null && res.size >= days) res else null
    }

    fun requestForecast(id: Long): Forecast = requestToSources { it.requestDayForecast(id) }

    private fun todayTimeSpan() = System.currentTimeMillis() / DAY_IN_MILLIS * DAY_IN_MILLIS

    private fun <T : Any> requestToSources(f: (ForecastDataSource) -> T?): T = sources.firstResult { f(it) }

}