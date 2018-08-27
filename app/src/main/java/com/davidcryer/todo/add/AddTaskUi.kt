package com.davidcryer.todo.add

import com.davidcryer.todo.common.TaskSubmission

interface AddTaskUi {
    fun showTitleError(title: String)
    fun dismiss()

    interface Listener {
        fun onClickSubmit(ui: AddTaskUi, submission: TaskSubmission)
    }
}