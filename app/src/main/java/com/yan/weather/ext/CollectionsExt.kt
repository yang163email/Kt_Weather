package com.yan.weather.ext

/**
 *  @author      : 楠GG
 *  @date        : 2017/12/14 10:58
 *  @description : TODO
 */
fun <K, V : Any> MutableMap<K, V?>.toVarargArray(): Array<out Pair<K, V>> =
        map({ Pair(it.key, it.value!!) }).toTypedArray()