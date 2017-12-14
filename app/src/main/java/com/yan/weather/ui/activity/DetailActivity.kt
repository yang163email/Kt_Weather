package com.yan.weather.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.widget.TextView
import com.squareup.picasso.Picasso
import com.yan.weather.R
import com.yan.weather.domain.commands.RequestDayForecastCommand
import com.yan.weather.domain.model.Forecast
import com.yan.weather.ext.color
import com.yan.weather.ext.toDateString
import com.yan.weather.utils.ToolbarManager
import kotlinx.android.synthetic.main.activity_detail.*
import org.jetbrains.anko.*
import java.text.DateFormat

/**
 *  @author      : 楠GG
 *  @date        : 2017/12/14 16:13
 *  @description : TODO
 */
class DetailActivity : AppCompatActivity(), ToolbarManager {

    companion object {
        val ID = "DetailActivity:id"
        val CITY_NAME = "DetailActivity:cityName"
    }

    override val toolbar by lazy { find<Toolbar>(R.id.toolbar) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        initToolbar()

        toolbarTitle = intent.getStringExtra(CITY_NAME)
        enableHomeAsUp { onBackPressed() }

        doAsync {
            val result = RequestDayForecastCommand(intent.getLongExtra(ID, -1)).execute()
            uiThread { bindForecast(result) }
        }

//        async(UI) {
//            val result = bg { RequestDayForecastCommand(intent.getLongExtra(ID, -1)).execute() }
//            bindForecast(result.await())
//        }
    }

    private fun bindForecast(forecast: Forecast) = with(forecast) {
        Picasso.with(ctx).load(iconUrl).into(icon)
        toolbar.subtitle = date.toDateString(DateFormat.FULL)
        weatherDescription.text = description
        bindWeather(high to maxTemperature, low to minTemperature)
    }

    private fun bindWeather(vararg views: Pair<Int, TextView>) = views.forEach {
        it.second.text = "${it.first.toString()}º"
        it.second.textColor = color(when (it.first) {
            in -50..0 -> android.R.color.holo_red_dark
            in 0..15 -> android.R.color.holo_orange_dark
            else -> android.R.color.holo_green_dark
        })
    }
}