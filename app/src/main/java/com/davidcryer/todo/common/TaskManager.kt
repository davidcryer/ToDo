package com.davidcryer.todo.common

import com.davidcryer.utils.MapUtils
import java.util.*

class TaskManager(private val taskStore: TaskStore) {
    private val taskWraps = mutableMapOf<UUID, TaskWrap>()
    private val addTaskListeners = mutableSetOf<AddTaskListener>()

    fun get(id: UUID): TaskWrap {
        return MapUtils.getValue(taskWraps, id) { TaskWrap(id, taskStore) }
    }

    @Throws(BadTaskException::class)
    fun add(submission: TaskSubmission): Task {
        return taskStore.submit(submission).also { notifyListenersOfTaskAdded(it) }
    }

    private fun notifyListenersOfTaskAdded(task: Task) {
        addTaskListeners.forEach { it.onTaskAdded(task) }
    }

    fun attach(listener: AddTaskListener) {
        addTaskListeners += listener
    }

    fun detach(listener: AddTaskListener) {
        addTaskListeners -= listener
    }
}