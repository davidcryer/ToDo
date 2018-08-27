package com.davidcryer.todo.common

interface TaskListener {
    fun onChangeDone(done: Boolean)
}