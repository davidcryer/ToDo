package com.davidcryer.todo.list

import java.util.*

class TasksModelFactory {

    fun create(): TasksModel {
        return TasksModel(mutableListOf(
                UiTask(UUID.randomUUID(), "First Task", false),
                UiTask(UUID.randomUUID(), "Second Task", false)
        ))
    }
}