package com.yan.weather.domain.mappers

import com.yan.weather.domain.model.ForecastList
import com.yan.weather.module.Forecast
import com.yan.weather.module.ForecastResult
import com.yan.weather.domain.model.Forecast as ModelForecast

/**
 *  @author      : 楠GG
 *  @date        : 2017/12/13 11:15
 *  @description : TODO
 */
class ForecastDataMapper {

    fun convertFromDataModel(zipCode: Long, forecast: ForecastResult): ForecastList {
        return ForecastList(zipCode, forecast.city.name, forecast.city.country,
                convertForecastListToDomain(forecast.list))
    }

    private fun convertForecastListToDomain(list: List<Forecast>): List<ModelForecast> {
        return list.map { convertForecastItemToDomain(it) }
    }

    private fun convertForecastItemToDomain(forecast: Forecast) = with(forecast) {
        ModelForecast(dt * 1000, weather[0].description,
                temp.max.toInt(), temp.min.toInt(),
                generateIconUrl(weather[0].icon))
    }

    private fun generateIconUrl(iconCode: String) =
            "http://openweathermap.org/img/w/$iconCode.png"
}