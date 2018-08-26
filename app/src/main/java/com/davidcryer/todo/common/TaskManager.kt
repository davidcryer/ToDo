package com.davidcryer.todo.common

import com.davidcryer.utils.MapUtils
import java.util.*

class TaskManager(private val store: TaskStore) {
    private val listeners = mutableMapOf<UUID, MutableSet<TaskListener>>()

    fun get(id: UUID): Task {
        return store.get(id)
    }

    fun attach(id: UUID, listener: TaskListener) {
        MapUtils.getColValue(listeners, id) { mutableSetOf() } += listener
    }

    fun detach(id: UUID, listener: TaskListener) {
        MapUtils.getColValue(listeners, id) { mutableSetOf() } -= listener
    }
}