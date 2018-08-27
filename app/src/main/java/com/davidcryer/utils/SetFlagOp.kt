package com.davidcryer.utils

class SetFlagOp(flag: Int) : FlagOp(flag) {

    override fun on(i: Int): Int {
        return i or flag
    }
}