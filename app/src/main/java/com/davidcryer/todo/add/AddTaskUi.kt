package com.davidcryer.todo.add

interface AddTaskUi {
    fun showTitleError(title: String)
    fun dismiss()

    interface Listener {
        fun onClickSubmit(ui: AddTaskUi, submission: TaskSubmission)
    }
}