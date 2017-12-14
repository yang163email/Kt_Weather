package com.yan.weather.module.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.yan.weather.app.WeatherApp
import org.jetbrains.anko.db.*

/**
 *  @author      : 楠GG
 *  @date        : 2017/12/13 18:36
 *  @description : 天气数据库helper类
 */
class ForecastDbHelper(ctx: Context = WeatherApp.instance) : ManagedSQLiteOpenHelper(
        ctx, DB_NAME, null, 1){

    companion object {
        val DB_NAME = "forecast.db"
        val DB_VERSION = 1
        val instance by lazy { ForecastDbHelper() }
    }

    override fun onCreate(db: SQLiteDatabase) {
        //创建两张表
        db.createTable(CityForecastTable.NAME,
                true,
                CityForecastTable.ID to INTEGER + PRIMARY_KEY,
                CityForecastTable.CITY to TEXT,
                CityForecastTable.COUNTRY to TEXT)

        db.createTable(DayForecastTable.NAME,
                true,
                DayForecastTable.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                DayForecastTable.DATE to INTEGER,
                DayForecastTable.DESCRIPTION to TEXT,
                DayForecastTable.HIGH to INTEGER,
                DayForecastTable.LOW to INTEGER,
                DayForecastTable.ICON_URL to TEXT,
                DayForecastTable.CITY_ID to INTEGER)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.dropTable(CityForecastTable.NAME, true)
        db.dropTable(DayForecastTable.NAME, true)
        onCreate(db)
    }

}