package com.davidcryer.todo

import android.os.Bundle
import com.davidc.uiwrapper.UiWrapper
import com.davidc.uiwrapper.UiWrapperInitializer
import com.davidcryer.todo.add.AddTaskModelFactory
import com.davidcryer.todo.add.AddTaskUi
import com.davidcryer.todo.add.AddTaskWrapper
import com.davidcryer.todo.common.TaskManager
import com.davidcryer.todo.list.TasksModelFactory
import com.davidcryer.todo.list.TasksUi
import com.davidcryer.todo.list.TasksWrapper

class UiWrapperFactory(private val taskManager: TaskManager) {
    private val tasksModelFactory = TasksModelFactory()
    private val addTaskModelFactory = AddTaskModelFactory()

    fun createTasks(savedState: Bundle?): UiWrapper<TasksUi, TasksUi.Listener, *> {
        return UiWrapperInitializer.from(
                savedState,
                { TasksWrapper.create(tasksModelFactory, taskManager) },
                { state -> TasksWrapper.recreate(state, taskManager) }
        )
    }

    fun createAddTask(savedState: Bundle?): UiWrapper<AddTaskUi, AddTaskUi.Listener, *> {
        return UiWrapperInitializer.from(
                savedState,
                { AddTaskWrapper.create(addTaskModelFactory, taskManager) },
                { state -> AddTaskWrapper.recreate(state, taskManager) }
        )
    }
}