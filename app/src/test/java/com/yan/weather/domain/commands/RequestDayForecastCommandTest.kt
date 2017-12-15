package com.yan.weather.domain.commands

import com.yan.weather.domain.datasource.ForecastProvider
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

/**
 *  @author      : 楠GG
 *  @date        : 2017/12/14 23:44
 *  @description : TODO
 */
class RequestDayForecastCommandTest {

    @Test fun testProviderIsCalled() {
        val forecastProvider = mock(ForecastProvider::class.java)
        val command = RequestDayForecastCommand(2, forecastProvider)

        command.execute()

        verify(forecastProvider).requestForecast(2)
    }
}