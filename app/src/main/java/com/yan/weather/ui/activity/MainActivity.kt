package com.yan.weather.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.yan.weather.R
import com.yan.weather.domain.commands.RequestForecastCommand
import com.yan.weather.ui.adapter.ForecastListAdapter
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread

/**
 *  @author      : 楠GG
 *  @date        : 2017/12/12 16:18
 *  @description : MainActivity
 */
class MainActivity : AppCompatActivity() {

    private val items = listOf(
            "Mon 6/23 - Sunny - 31/17",
            "Tue 6/24 - Foggy - 21/8",
            "Wed 6/25 - Cloudy - 22/17",
            "Thurs 6/26 - Rainy - 18/11",
            "Fri 6/27 - Foggy - 21/10",
            "Sat 6/28 - TRAPPED IN WEATHERSTATION - 23/18",
            "Sun 6/29 - Sunny - 20/7"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        forecast_list.layoutManager = LinearLayoutManager(this)
        requestData()
    }

    private fun requestData() {
        doAsync {
            val result = RequestForecastCommand(94043).execute()
            uiThread {
                forecast_list.adapter = ForecastListAdapter(result) {
                    toast(it.description)
                }
            }
        }
    }
}
