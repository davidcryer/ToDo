package com.davidcryer.todo.list

import android.os.Bundle
import com.davidc.uiwrapper.UiWrapper

class TasksWrapper(uiModel: TasksModel) : UiWrapper<TasksUi, TasksUi.Listener, TasksModel>(uiModel) {

    override fun uiListener(): TasksUi.Listener {
        return object: TasksUi.Listener {}
    }

    companion object {
        fun create(tasksModelFactory: TasksModelFactory): TasksWrapper {
            return TasksWrapper(tasksModelFactory.create())
        }

        fun recreate(tasksModelFactory: TasksModelFactory, savedState: Bundle): TasksWrapper {
            val tasksModel: TasksModel? = savedUiModel(savedState)
            return if (tasksModel == null) create(tasksModelFactory) else TasksWrapper(tasksModel)
        }
    }
}