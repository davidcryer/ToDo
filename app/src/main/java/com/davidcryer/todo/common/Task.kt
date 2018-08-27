package com.davidcryer.todo.common

import java.util.*

class Task(val id: UUID, val title: String, var done: Boolean) {

    fun toggleDone(): Boolean {
        return let { !done }.apply { this@Task.done = this }
    }

    fun deflate(): DbTask {
        return DbTask(id, title, done)
    }
}