package com.davidcryer.todo.common

import com.davidcryer.utils.MapUtils
import java.time.Instant
import java.util.*

class TaskManager(private val taskStore: TaskStore) {
    private val taskWraps = mutableMapOf<UUID, TaskWrap>()
    private val addTaskListeners = mutableSetOf<AddTaskListener>()
    private var listChanged = Instant.now()

    fun get(id: UUID): TaskWrap {
        return MapUtils.getValue(taskWraps, id) { TaskWrap(id, taskStore) }
    }

    fun getAll(): List<Task> {
        return taskStore.getAll()
    }

    fun getUpdates(timestamp: Instant?, callback: (List<Task>, Instant) -> Unit) {
        if (timestamp == null || timestamp < listChanged) {
            callback(getAll(), listChanged)
        }
    }

    @Throws(BadTaskException::class)
    fun add(submission: TaskSubmission): Task {
        return taskStore.submit(submission).also {
            listChanged = Instant.now()
            notifyListenersOfTaskAdded(it, listChanged)
        }
    }

    private fun notifyListenersOfTaskAdded(task: Task, timestamp: Instant) {
        addTaskListeners.forEach { it.onTaskAdded(task, timestamp) }
    }

    fun attach(listener: AddTaskListener) {
        addTaskListeners += listener
    }

    fun detach(listener: AddTaskListener) {
        addTaskListeners -= listener
    }
}