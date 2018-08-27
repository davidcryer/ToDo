package com.davidcryer.todo.common

import java.util.*

class TaskStore(private val database: TaskDatabase, private val cache: TaskCache) {

    fun get(id: UUID): Task? {
        return cache.get(id) { database.get(id) }
    }

    @Throws(BadTaskException::class)
    fun submit(submission: TaskSubmission): Task {
        return database.insert(submission).also { cache.set(it) }
    }

    fun set(task: Task) {
        task.apply {
            database.update(task)
            cache.set(task)
        }
    }

    fun remove(task: Task) {
        task.apply {
            database.delete(task)
            cache.remove(task)
        }
    }
}