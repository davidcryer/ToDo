package com.davidcryer.todo.list

import android.os.Bundle
import com.davidc.uiwrapper.UiWrapper
import com.davidcryer.todo.common.TaskManager

class TasksWrapper(
        private val model: TasksModel,
        private val taskManager: TaskManager
) : UiWrapper<TasksUi, TasksUi.Listener, TasksModel>(model) {

    override fun uiListener(): TasksUi.Listener {
        return object : TasksUi.Listener {
            override fun onClickAddTask(ui: TasksUi) { ui.openAddTask() }
        }
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