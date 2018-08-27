package com.davidcryer.todo.add

import android.os.Bundle
import com.davidc.uiwrapper.UiWrapper
import com.davidcryer.todo.common.BadTaskException
import com.davidcryer.todo.common.TaskManager

class AddTaskWrapper(
        model: AddTaskModel,
        private val taskManager: TaskManager
) : UiWrapper<AddTaskUi, AddTaskUi.Listener, AddTaskModel>(model) {

    override fun uiListener(): AddTaskUi.Listener {
        return object : AddTaskUi.Listener {
            override fun onClickSubmit(ui: AddTaskUi, submission: TaskSubmission) {
                try {
                    taskManager.add(submission)
                    ui.dismiss()
                } catch (e: BadTaskException) {
                    e.title?.let { ui.showTitleError("Enter a title") }//TODO or set error in model
                }
            }
        }
    }

    companion object {
        fun create(addTaskModelFactory: AddTaskModelFactory, taskManager: TaskManager): AddTaskWrapper {
            return AddTaskWrapper(addTaskModelFactory.create(), taskManager)
        }

        fun recreate(savedState: Bundle, taskManager: TaskManager): AddTaskWrapper? {
            return (savedUiModel(savedState) as? AddTaskModel)?.let { AddTaskWrapper(it, taskManager) }
        }
    }
}