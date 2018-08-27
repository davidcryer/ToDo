package com.davidcryer.utils

class UnsetFlagOp(flag: Int) : FlagOp(flag) {

    override fun on(i: Int): Int {
        return i and flag.inv()
    }
}