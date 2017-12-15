package com.yan.weather.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import com.yan.weather.R
import com.yan.weather.domain.commands.RequestForecastCommand
import com.yan.weather.domain.model.ForecastList
import com.yan.weather.ext.DelegatesExt
import com.yan.weather.ui.adapter.ForecastListAdapter
import com.yan.weather.utils.ToolbarManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.uiThread

/**
 *  @author      : 楠GG
 *  @date        : 2017/12/12 16:18
 *  @description : MainActivity
 */
class MainActivity : AppCompatActivity(), ToolbarManager {
    override val toolbar by lazy { find<Toolbar>(R.id.toolbar) }

    private val zipCode by DelegatesExt.preference(this,
            SettingsActivity.ZIP_CODE, SettingsActivity.DEFAULT_ZIP)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initToolbar()

        forecast_list.layoutManager = LinearLayoutManager(this)
        attachToScroll(forecast_list)
    }

    override fun onResume() {
        super.onResume()
//        requestData()
        loadForecast()
    }

    private fun loadForecast() = async(UI) {
        val result = bg { RequestForecastCommand(zipCode).execute() }
        updateUI(result.await())
    }

    private fun updateUI(result: ForecastList) {
        val adapter = ForecastListAdapter(result) {
            startActivity<DetailActivity>(
                    DetailActivity.ID to it.id,
                    DetailActivity.CITY_NAME to result.city)
        }
        forecast_list.adapter = adapter
        toolbarTitle = "${result.city} (${result.country})"
    }

    private fun requestData() = doAsync {
        val result = RequestForecastCommand(zipCode).execute()
        uiThread {
            val adapter = ForecastListAdapter(result) {
                startActivity<DetailActivity>(
                        DetailActivity.ID to it.id,
                        DetailActivity.CITY_NAME to result.city)
            }
            forecast_list.adapter = adapter
            toolbarTitle = "${result.city} (${result.country})"
        }
    }
}
