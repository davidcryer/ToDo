package com.davidcryer.todo.common

import com.davidcryer.todo.add.TaskSubmission
import com.davidcryer.utils.MapUtils
import java.util.*

class TaskManager(private val store: TaskStore, private val taskFactory: TaskFactory) {
    private val taskWraps = mutableMapOf<UUID, TaskWrap>()
    private val addTaskListeners = mutableSetOf<AddTaskListener>()

    fun get(id: UUID): TaskWrap {
        return MapUtils.getValue(taskWraps, id) { TaskWrap { store.get(id) } }
    }

    @Throws(BadTaskException::class)
    fun add(submission: TaskSubmission): Task {
        return taskFactory.from(submission).let { store.set(it) }.also { notifyListenersOfTaskAdded(it) }
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