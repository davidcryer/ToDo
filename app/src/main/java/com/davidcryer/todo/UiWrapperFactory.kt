package com.davidcryer.todo

import android.os.Bundle
import com.davidc.uiwrapper.UiWrapper
import com.davidc.uiwrapper.UiWrapperInitializer
import com.davidcryer.todo.list.TasksModelFactory
import com.davidcryer.todo.list.TasksUi
import com.davidcryer.todo.list.TasksWrapper

class UiWrapperFactory {
    private val tasksModelFactory: TasksModelFactory = TasksModelFactory()

    fun createTasks(savedState: Bundle?): UiWrapper<TasksUi, TasksUi.Listener, *> {
        return UiWrapperInitializer.from(
                savedState,
                { TasksWrapper.create(tasksModelFactory) },
                { state -> TasksWrapper.recreate(tasksModelFactory, state) }
        )
    }
}