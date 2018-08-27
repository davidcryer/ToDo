package com.davidcryer.todo.common

import com.davidcryer.todo.add.TaskSubmission
import com.davidcryer.utils.MapUtils
import java.util.*

class TaskManager(private val store: TaskStore) {
    private val taskListeners = mutableMapOf<UUID, MutableSet<TaskListener>>()
    private val addTaskListeners = mutableSetOf<AddTaskListener>()

    fun get(id: UUID): Task? {
        return store.get(id)
    }

    @Throws(BadTaskException::class)
    fun add(submission: TaskSubmission): Task {
        return Task.from(submission).let { store.set(it) }.also { notifyListenersOfTaskAdded(it) }
    }

    private fun notifyListenersOfTaskAdded(task: Task) {
        addTaskListeners.forEach { it.onTaskAdded(task) }
    }

    fun attach(id: UUID, listener: TaskListener) {
        MapUtils.getColValue(taskListeners, id) { mutableSetOf() } += listener
    }

    fun detach(id: UUID, listener: TaskListener) {
        MapUtils.getColValue(taskListeners, id) { mutableSetOf() } -= listener
    }

    fun attach(listener: AddTaskListener) {
        addTaskListeners += listener
    }

    fun detach(listener: AddTaskListener) {
        addTaskListeners -= listener
    }
}