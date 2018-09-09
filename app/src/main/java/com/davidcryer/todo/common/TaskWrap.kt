package com.davidcryer.todo.common

import java.time.Instant
import java.util.*

class TaskWrap(private val id: UUID, private val store: TaskStore) {
    private val listeners = mutableSetOf<TaskListener>()
    private var taskUpdated = Instant.now()

    fun task(request: TaskRequest) {
        store.get(id)?.let { request.response(it.id, it.title, it.done) }
    }

    fun getUpdates(timestamp: Instant?, callback: (Task, Instant) -> Unit) {
        if (timestamp == null || timestamp < taskUpdated) {
            store.get(id)?.let { callback(it, taskUpdated) }
        }
    }

    fun toggleDone(ignore: TaskListener, callback: (Boolean) -> Unit) {
        store.get(id)?.also { task ->
            taskUpdated = Instant.now()
            val done = task.toggleDone()
            store.set(task)
            notifyListeners(ignore) { it.onChangeDone(done, taskUpdated) }
            callback(done)
        }
    }

    private fun notifyListeners(ignore: TaskListener, notification: (TaskListener) -> Unit) {
        listeners.forEach { l -> l.takeIf { it != ignore }.run { notification(l) } }
    }

    fun attach(listener: TaskListener) {
        listeners += listener
    }

    fun detach(listener: TaskListener) {
        listeners -= listener
    }

    interface TaskRequest {
        fun response(id: UUID, title: String, done: Boolean)
    }
}