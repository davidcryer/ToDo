package com.davidcryer.todo.common

import java.util.*

class TaskWrap(private val id: UUID, private val store: TaskStore) {
    private val listeners = mutableSetOf<TaskListener>()

    fun task(request: TaskRequest) {
        store.get(id)?.let { request.response(it.id, it.title, it.done) }
    }

    fun toggleDone(ignore: TaskListener, callback: (Boolean) -> Unit) {
        store.get(id)?.also { task ->
            val done = task.toggleDone()
            store.set(task)
            notify(ignore) { it.onChangeDone(done) }
            callback(done)
        }
    }

    private fun notify(ignore: TaskListener, notification: (TaskListener) -> Unit) {
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