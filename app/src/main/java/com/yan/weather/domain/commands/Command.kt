package com.yan.weather.domain.commands

/**
 *  @author      : 楠GG
 *  @date        : 2017/12/13 11:11
 *  @description : TODO
 */
interface Command<T> {
    fun execute(): T
}