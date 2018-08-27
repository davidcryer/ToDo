package com.davidcryer.todo.common

import java.util.*

class TaskWrap(private val taskProvider: () -> Task?) {
    private val listeners = mutableSetOf<TaskListener>()

    fun task(request: TaskRequest) {
        taskProvider()?.let { request.response(it.id, it.title, it.done) }
    }

    fun toggleDone(ignore: TaskListener, callback: (Boolean) -> Unit) {
        taskProvider()?.toggleDone()?.also {
            done -> notify(ignore) { it.onChangeDone(done) }
        }?.also {
            callback(it)
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