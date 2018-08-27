package com.davidcryer.utils

object MapUtils {

    fun <K, V> getValue(map: MutableMap<K, V>, k: K, newVal: () -> V): V {
        return map[k] ?: newVal().also { map[k] = it }
    }

    fun <K, V> getOptValue(map: MutableMap<K, V?>, k: K, newVal: () -> V?): V? {
        return map[k] ?: newVal()?.also { map[k] = it }
    }
}