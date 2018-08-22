package com.davidcryer.todo.list

import android.os.Bundle
import com.davidc.uiwrapper.UiWrapper
import com.davidc.uiwrapper.UiWrapperFactoryFragment
import com.davidcryer.todo.UiWrapperFactory

class TasksFragment() : UiWrapperFactoryFragment<TasksUi, TasksUi.Listener, UiWrapperFactory>() {


    override fun uiWrapper(uiWrapperFactory: UiWrapperFactory, savedState: Bundle?): UiWrapper<TasksUi, TasksUi.Listener, *> {
        return uiWrapperFactory.createTasks(savedState)
    }

    override fun ui(): TasksUi {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}