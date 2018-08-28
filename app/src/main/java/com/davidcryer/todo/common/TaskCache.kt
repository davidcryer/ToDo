package com.davidcryer.todo.common

import java.util.*

interface TaskCache {
    fun get(id: UUID, taskLoader: () -> Task?): Task?
    fun set(task: Task)
    fun setAll(tasks: Collection<Task>)
    fun remove(task: Task)
}