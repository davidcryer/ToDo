package com.davidcryer.todo

import android.content.Context
import com.davidc.uiwrapper.UiWrapperFactoryProvider
import com.davidcryer.todo.common.*
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
            return TaskManager(createTaskStore(context))
        }

        private fun createTaskStore(context: Context): TaskStore {
            return TaskStore(createTaskDatabase(context), createTaskCache())
        }

        private fun createTaskCache(): TaskCache {
            return InMemoryTaskCache()
        }

        private fun createTaskDatabase(context: Context): TaskDatabase {
            return SharedPreferencesTaskDatabase.create(context, Gson(), createTaskFactory())
        }

        private fun createTaskFactory(): TaskFactory {
            return TaskFactory()
        }
    }
}