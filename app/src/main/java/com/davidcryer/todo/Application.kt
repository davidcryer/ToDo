package com.davidcryer.todo

import android.content.Context
import com.davidc.uiwrapper.UiWrapperFactoryProvider
import com.davidcryer.todo.common.SharedPreferencesTaskStore
import com.davidcryer.todo.common.TaskFactory
import com.davidcryer.todo.common.TaskManager
import com.davidcryer.todo.common.TaskStore
import com.google.gson.Gson

class Application : android.app.Application(), UiWrapperFactoryProvider<UiWrapperFactory> {
    private var uiWrapperFactory: UiWrapperFactory? = null

    override fun onCreate() {
        super.onCreate()
        uiWrapperFactory = createUiWrapperFactory(this)
    }

    override fun getUiWrapperFactory(): UiWrapperFactory {
        return uiWrapperFactory!!
    }

    companion object {

        private fun createUiWrapperFactory(context: Context): UiWrapperFactory {
            return UiWrapperFactory(createTaskManager(context))
        }

        private fun createTaskManager(context: Context): TaskManager {
            val factory = createTaskFactory()
            return TaskManager(createTaskStore(context, factory), factory)
        }

        private fun createTaskFactory(): TaskFactory {
            return TaskFactory()
        }

        private fun createTaskStore(context: Context, taskFactory: TaskFactory): TaskStore {
            return SharedPreferencesTaskStore.create(context, Gson(), taskFactory)
        }
    }
}