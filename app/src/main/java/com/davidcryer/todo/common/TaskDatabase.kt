package com.davidcryer.todo.common

import java.util.*

interface TaskDatabase {
    fun get(id: UUID): Task?
    fun getAll(): List<Task>
    fun insert(submission: TaskSubmission): Task
    fun update(task: Task)
    fun delete(task: Task)
}