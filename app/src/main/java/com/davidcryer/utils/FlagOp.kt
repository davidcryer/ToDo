package com.davidcryer.utils

abstract class FlagOp(val flag: Int) {

    abstract fun on(i: Int): Int
}