package com.davidcryer.todo.common

import java.util.*

interface TaskStore {
    fun get(id: UUID): Task?
    fun getAll(): List<Task>
    fun set(task: Task): Task
    fun delete(task: Task)
}