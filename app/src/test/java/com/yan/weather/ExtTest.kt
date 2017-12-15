package com.yan.weather

import com.yan.weather.ext.toDateString
import org.junit.Assert.assertEquals
import org.junit.Test
import java.text.DateFormat

/**
 *  @author      : 楠GG
 *  @date        : 2017/12/14 22:20
 *  @description : TODO
 */
class ExtTest {

    @Test fun testLongToDateString() {
        assertEquals("Oct 19, 2015", 1445275635000L.toDateString())
    }

    @Test fun testDateStringFullFormat() {
        assertEquals("Monday, October 19, 2015",
                1445275635000L.toDateString(DateFormat.FULL))
    }
}