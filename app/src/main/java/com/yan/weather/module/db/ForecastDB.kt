package com.yan.weather.module.db

import com.yan.weather.domain.model.ForecastList
import com.yan.weather.ext.clear
import com.yan.weather.ext.parseList
import com.yan.weather.ext.parseOpt
import com.yan.weather.ext.toVarargArray
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

/**
 *  @author      : 楠GG
 *  @date        : 2017/12/14 9:33
 *  @description : TODO
 */
class ForecastDB(
        val forecastDbHelper: ForecastDbHelper = ForecastDbHelper.instance,
        val dataMapper: DBDataMapper = DBDataMapper()
) {

    fun requestForecastByZipCode(zipCode: Long, date: Long) = forecastDbHelper.use {
        val dailyRequest = "${DayForecastTable.CITY_ID} = {id}"+
                "AND ${DayForecastTable.DATE} >= {date}"

        val dailyForecast = select(DayForecastTable.NAME)
                .whereArgs(dailyRequest, "id" to zipCode, "date" to date)
                .parseList { DayForecast(HashMap(it)) }

        val city = select(DayForecastTable.NAME)
                .whereSimple("${CityForecastTable.ID} = ?", zipCode.toString())
                .parseOpt{ CityForecast(HashMap(it), dailyForecast) }

        if (city != null) dataMapper.convertToDomain(city) else null
    }

    fun saveForecast(forecast: ForecastList) = forecastDbHelper.use {
        //情况两张表
        clear(CityForecastTable.NAME)
        clear(DayForecastTable.NAME)

        with(dataMapper.convertFromDomain(forecast)) {
            insert(CityForecastTable.NAME, *map.toVarargArray())
            dailyForecast.forEach {
                insert(DayForecastTable.NAME, *it.map.toVarargArray())
            }
        }
    }
}