package com.davidcryer.todo.common

import java.util.*

interface TaskStore {
    fun get(id: UUID): Task
    fun set(task: UUID): Task
}