package com.davidcryer.todo.list

interface TasksUi {
    fun show(tasks: List<UiTask>)

    interface Listener
}