package com.davidcryer.todo.common

import com.davidcryer.todo.add.TaskSubmission
import com.davidcryer.utils.MapUtils
import java.util.*

class TaskManager(private val store: TaskStore) {
    private val listeners = mutableMapOf<UUID, MutableSet<TaskListener>>()

    fun get(id: UUID): Task {
        return store.get(id)
    }

    @Throws(BadTaskException::class)
    fun add(submission: TaskSubmission): Task {
        return Task.from(submission)
    }

    fun attach(id: UUID, listener: TaskListener) {
        MapUtils.getColValue(listeners, id) { mutableSetOf() } += listener
    }

    fun detach(id: UUID, listener: TaskListener) {
        MapUtils.getColValue(listeners, id) { mutableSetOf() } -= listener
    }
}