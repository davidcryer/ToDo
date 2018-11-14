package com.davidcryer.todo.list

import android.os.Bundle
import com.davidc.uiwrapper.UiWrapper
import com.davidcryer.todo.common.AddTaskListener
import com.davidcryer.todo.common.Task
import com.davidcryer.todo.common.TaskManager
import java.time.Instant

class TasksWrapper(
        model: TasksModel,
        private val taskManager: TaskManager
) : UiWrapper<TasksUi, TasksUi.Listener, TasksModel>(model) {
    private val listener = object : TasksUi.Listener {
        override fun onClickAddTask(ui: TasksUi) { ui.openAddTask() }
    }
    private val addTaskListener = object : AddTaskListener {
        override fun onTaskAdded(task: Task, timestamp: Instant) {
            model.append(ui(), UiTask(task).also { it.attach(taskManager) })
            lastUpdated = timestamp
        }
    }
    private var lastUpdated: Instant? = null

    override fun registerResources() {
        super.registerResources()
        taskManager.getUpdates(lastUpdated) { tasks, timestamp ->
            uiModel().set(tasks.map(::UiTask).toMutableList())
            lastUpdated = timestamp
        }
        taskManager.attach(addTaskListener)
        uiModel().tasks.forEach { it.attach(taskManager) }
    }

    override fun unregisterResources() {
        super.unregisterResources()
        taskManager.detach(addTaskListener)
        uiModel().tasks.forEach { it.detach(taskManager) }
    }

    override fun setUp(ui: TasksUi) {
        ui.set(uiModel().tasks)
    }

    override fun uiListener(): TasksUi.Listener {
        return listener
    }

    companion object {
        fun create(tasksModelFactory: TasksModelFactory, taskManager: TaskManager): TasksWrapper {
            return TasksWrapper(tasksModelFactory.create(), taskManager)
        }

        fun recreate(savedState: Bundle, taskManager: TaskManager): TasksWrapper? {
            return (savedUiModel(savedState) as? TasksModel)?.let { TasksWrapper(it, taskManager) }
        }
    }
}