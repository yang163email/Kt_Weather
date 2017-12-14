package com.yan.weather.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import com.yan.weather.R
import com.yan.weather.domain.model.Forecast
import com.yan.weather.domain.model.ForecastList
import com.yan.weather.ext.ctx
import com.yan.weather.ext.toDateString
import kotlinx.android.synthetic.main.item_forecast.view.*

/**
 *  @author      : 楠GG
 *  @date        : 2017/12/12 16:38
 *  @description : 天气RecyclerView的adapter
 */
class ForecastListAdapter(
        val weekForecast: ForecastList,
        val itemClick: (Forecast) -> Unit
        ) : RecyclerView.Adapter<ForecastListAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindForecast(weekForecast[position])
    }

    override fun getItemCount(): Int = weekForecast.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.ctx).inflate(R.layout.item_forecast, parent, false)
        return ViewHolder(itemView, itemClick)
    }

    class ViewHolder(view: View, val itemClick: (Forecast) -> Unit) : RecyclerView.ViewHolder(view) {

        fun bindForecast(forecast: Forecast) {
            with(forecast) {
                with(itemView) {
                    Picasso.with(itemView.ctx).load(iconUrl).into(icon)
                    tv_date.text = date.toDateString()
                    tv_description.text = description
                    maxTemperature.text = high.toString()
                    minTemperature.text = low.toString()
                    setOnClickListener { itemClick(forecast) }
                }
            }
        }
    }

}