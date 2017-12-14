package com.yan.weather.ext

import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.MapRowParser
import org.jetbrains.anko.db.SelectQueryBuilder

/**
 *  @author      : 楠GG
 *  @date        : 2017/12/14 9:43
 *  @description : TODO
 */
fun <T: Any> SelectQueryBuilder.parseList(parser: (Map<String, Any?>) -> T) =
        parseList(object : MapRowParser<T> {
            override fun parseRow(columns: Map<String, Any?>): T = parser(columns)
        })

fun <T: Any> SelectQueryBuilder.parseOpt(parser: (Map<String, Any?>) -> T) =
        parseOpt(object : MapRowParser<T> {
            override fun parseRow(columns: Map<String, Any?>): T = parser(columns)
        })

fun SQLiteDatabase.clear(tableName: String) {
    execSQL("delete from $tableName")
}