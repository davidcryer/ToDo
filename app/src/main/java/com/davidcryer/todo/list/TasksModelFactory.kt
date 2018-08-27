package com.davidcryer.todo.list

class TasksModelFactory {

    fun create(): TasksModel {
        return TasksModel(mutableListOf())
    }
}