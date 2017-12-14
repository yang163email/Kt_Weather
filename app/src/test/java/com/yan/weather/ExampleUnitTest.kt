package com.yan.weather

import org.junit.Assert.*
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    val list = listOf(1, 2, 3, 4, 5, 6)
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun listAny() {
        assertTrue(list.any{ it % 2 == 0 })
        assertFalse(list.any {it > 10})
    }

    @Test
    fun listAll() {
        assertTrue(list.all { it < 10 })
        assertTrue(list.all { it % 2 == 0 })
    }

    @Test
    fun listCount(){
        assertEquals(3, list.count { it % 2 == 0 } )
    }

    @Test
    fun listFold() {
        assertEquals(25, list.fold(4) {acc: Int, i: Int -> acc + i })
    }

    @Test
    fun listFoldRight() {
        assertEquals(25, list.foldRight(4) { total, next -> total + next })
    }

    @Test
    fun testContains() {
        assertTrue(1 in list)
    }
}
