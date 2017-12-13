package com.yan.weather.net

import android.util.Log
import java.net.URL

/**
 *  @author      : 楠GG
 *  @date        : 2017/12/12 17:30
 *  @description : TODO
 */
class Request(val url: String) {
    val TAG = javaClass.simpleName

    fun run() {
        val jsonStr = URL(url).readText()
        Log.d(TAG, "run: $jsonStr")
    }
}