package com.yan.weather.ext

import java.text.DateFormat
import java.util.*

/**
 *  @author      : 楠GG
 *  @date        : 2017/12/14 16:58
 *  @description : TODO
 */
fun Long.toDateString(dateFormat: Int = DateFormat.MEDIUM): String {
    val df = DateFormat.getDateInstance(dateFormat, Locale.getDefault())
    return df.format(this)
}