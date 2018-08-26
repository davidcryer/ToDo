package com.davidcryer.utils

object MapUtils {

    fun <K, V, ColV> getColValue(
            map: MutableMap<K, V>,
            k: K,
            emptyCol: () -> V
    ): MutableCollection<ColV> where V : MutableCollection<ColV> {
        return map[k] ?: emptyCol().also { map[k] = it }
    }
}