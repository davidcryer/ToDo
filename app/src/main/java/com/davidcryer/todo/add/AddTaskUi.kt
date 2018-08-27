package com.davidcryer.todo.add

interface AddTaskUi {
    fun showError(title: String)
    fun dismiss()

    interface Listener {
        fun onClickSubmit(ui: AddTaskUi, submission: TaskSubmission)
    }
}