package com.davidcryer.todo.common

import java.util.*

class Task(val id: UUID, val title: String, var done: Boolean) {

    fun toggleDone(): Boolean {
        return let { !done }.also { this@Task.done = it }
    }

    fun deflate(): DbTask {
        return DbTask(id, title, done)
    }
}