package com.davidcryer.todo.list

import java.util.*

class TasksModelFactory {

    fun create(): TasksModel {
        return TasksModel(listOf(
                UiTask(UUID.randomUUID(), "First Task"),
                UiTask(UUID.randomUUID(), "Second Task")
        ))
    }
}