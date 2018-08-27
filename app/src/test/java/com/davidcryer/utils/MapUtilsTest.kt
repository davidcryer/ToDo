package com.davidcryer.utils

import org.junit.Test

import org.junit.Assert.*

class MapUtilsTest {

    @Test
    fun getColValue_newVal() {
        val key = "key"
        val map = mutableMapOf<String, Any>()
        val res = MapUtils.getValue(map, key) { Any() }
        assertSame(res, map[key])
    }

    @Test
    fun getValue_getExistingCollection() {
        val key = "key"
        val map = mutableMapOf<String, Any>()
        val value = Any()
        map[key] = value
        val res = MapUtils.getValue(map, key) { Any() }
        assertSame(value, map[key])
        assertSame(value, res)
    }
}