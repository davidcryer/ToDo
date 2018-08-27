package com.davidcryer.todo.list

import android.os.Bundle
import com.davidc.uiwrapper.UiWrapper
import com.davidcryer.todo.common.AddTaskListener
import com.davidcryer.todo.common.Task
import com.davidcryer.todo.common.TaskManager

class TasksWrapper(
        private val model: TasksModel,
        private val taskManager: TaskManager
) : UiWrapper<TasksUi, TasksUi.Listener, TasksModel>(model) {
    private val listener = object : TasksUi.Listener {
        override fun onClickAddTask(ui: TasksUi) { ui.openAddTask() }
    }
    private val addTaskListener = object : AddTaskListener {
        override fun onTaskAdded(task: Task) {
            model.append(ui(), UiTask(task).also { it.attach(taskManager) })
        }
    }

    override fun registerResources() {
        super.registerResources()
        taskManager.attach(addTaskListener)
        model.tasks.forEach { it.attach(taskManager) }
    }

    override fun unregisterResources() {
        super.unregisterResources()
        taskManager.detach(addTaskListener)
        model.tasks.forEach { it.detach(taskManager) }
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