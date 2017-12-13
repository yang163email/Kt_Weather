package com.yan.weather.domain.mappers

import com.yan.weather.domain.model.ForecastList
import com.yan.weather.module.Forecast
import com.yan.weather.module.ForecastResult
import java.text.DateFormat
import java.util.*
import com.yan.weather.domain.model.Forecast as ModelForecast

/**
 *  @author      : 楠GG
 *  @date        : 2017/12/13 11:15
 *  @description : TODO
 */
class ForecastDataMapper {

    fun convertFromDataModel(forecast: ForecastResult): ForecastList {
        return ForecastList(forecast.city.name, forecast.city.country,
                convertForecastListToDomain(forecast.list))
    }

    private fun convertForecastListToDomain(list: List<Forecast>): List<ModelForecast> {
        return list.map { convertForecastItemToDomain(it) }
    }

    private fun convertForecastItemToDomain(forecast: Forecast): ModelForecast {
        return ModelForecast(convertData(forecast.dt), forecast.weather[0].description,
                forecast.temp.max.toInt(), forecast.temp.min.toInt(),
                generateIconUrl(forecast.weather[0].icon))
    }

    private fun convertData(data: Long): String {
        val df = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.getDefault())
        return df.format(data * 1000)
    }

    private fun generateIconUrl(iconCode: String): String =
            "http://openweathermap.org/img/w/$iconCode.png"
}