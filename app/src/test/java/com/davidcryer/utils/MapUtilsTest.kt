package com.davidcryer.utils

import org.junit.Test

import org.junit.Assert.*

class MapUtilsTest {

    @Test
    fun getColValue_createNewCollection() {
        val key = "key"
        val map = mutableMapOf<String, MutableCollection<Any>>()
        val res = MapUtils.getColValue(map, key) { mutableListOf() }
        assertTrue(res.isEmpty())
        assertSame(res, map[key])
    }

    @Test
    fun getColValue_getExistingCollection() {
        val key = "key"
        val map = mutableMapOf<String, MutableCollection<Any>>()
        val col = mutableListOf<Any>()
        map[key] = col
        val res = MapUtils.getColValue(map, key) { mutableListOf() }
        assertTrue(col.isEmpty())
        assertSame(col, map[key])
        assertSame(col, res)
    }
}