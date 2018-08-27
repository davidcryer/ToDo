package com.davidcryer.todo.list

interface TasksUi {
    fun show(tasks: List<UiTask>)
    fun openAddTask()

    interface Listener {
        fun onClickAddTask(ui: TasksUi)
    }
}