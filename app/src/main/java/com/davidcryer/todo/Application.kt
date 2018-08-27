package com.davidcryer.todo

import com.davidc.uiwrapper.UiWrapperFactoryProvider
import com.davidcryer.todo.common.SharedPreferencesTaskStore
import com.davidcryer.todo.common.TaskManager

class Application : android.app.Application(), UiWrapperFactoryProvider<UiWrapperFactory> {
    private var uiWrapperFactory: UiWrapperFactory? = null

    override fun onCreate() {
        super.onCreate()
        uiWrapperFactory = UiWrapperFactory(TaskManager(SharedPreferencesTaskStore()))
    }

    override fun getUiWrapperFactory(): UiWrapperFactory {
        return uiWrapperFactory!!
    }
}