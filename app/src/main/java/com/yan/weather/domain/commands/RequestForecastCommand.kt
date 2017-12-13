package com.yan.weather.domain.commands

import com.yan.weather.domain.mappers.ForecastDataMapper
import com.yan.weather.domain.model.ForecastList
import com.yan.weather.net.ForecastRequest

/**
 *  @author      : 楠GG
 *  @date        : 2017/12/13 11:34
 *  @description : TODO
 */
class RequestForecastCommand(val zipCode: String) : Command<ForecastList> {
    override fun execute(): ForecastList {
        val request = ForecastRequest(zipCode)
        return ForecastDataMapper().convertFromDataModel(request.execute())
    }
}