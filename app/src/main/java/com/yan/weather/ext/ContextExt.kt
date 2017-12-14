package com.yan.weather.ext

import android.content.Context
import android.support.v4.content.ContextCompat

/**
 *  @author      : 楠GG
 *  @date        : 2017/12/14 17:02
 *  @description : TODO
 */
fun Context.color(res: Int): Int = ContextCompat.getColor(this, res)