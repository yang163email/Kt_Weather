package com.yan.weather.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import android.widget.TextView
import com.yan.weather.domain.model.ForecastList

/**
 *  @author      : 楠GG
 *  @date        : 2017/12/12 16:38
 *  @description : 天气RecyclerView的adapter
 */
class ForecastListAdapter(val weekForecast: ForecastList) : RecyclerView.Adapter<ForecastListAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(weekForecast.dailyForecast[position]) {
            holder.textView.text = "$date - $description - $high/$low"
        }
    }

    override fun getItemCount(): Int = weekForecast.dailyForecast.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
            ViewHolder(TextView(parent.context))

    class ViewHolder(val textView: TextView) : RecyclerView.ViewHolder(textView)
}