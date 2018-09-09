package com.davidcryer.todo.common

import java.time.Instant

interface AddTaskListener {
    fun onTaskAdded(task: Task, timestamp: Instant)
}