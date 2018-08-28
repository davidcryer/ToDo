package com.davidcryer.todo.list

interface TasksUi {
    fun set(tasks: List<UiTask>)
    fun append(task: UiTask)
    fun openAddTask()

    interface Listener {
        fun onClickAddTask(ui: TasksUi)
    }
}