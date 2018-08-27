package com.davidcryer.todo.common

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import java.util.*

class SharedPreferencesTaskStore(
        private val sharedPreferences: SharedPreferences,
        private val gson: Gson,
        private val taskFactory: TaskFactory
) : TaskStore {
    companion object {
        private val PREFS_KEY = "tasks"

        fun create(context: Context, gson: Gson, taskFactory: TaskFactory): SharedPreferencesTaskStore {
            val sharedPreferences = context.getSharedPreferences(PREFS_KEY, Context.MODE_PRIVATE)
            return SharedPreferencesTaskStore(sharedPreferences, gson, taskFactory)
        }
    }

    override fun get(id: UUID): Task? {//TODO optimise (probably with cache)
        return sharedPreferences.getString(id.toString(), null)?.let { fromJson(it) }
    }

    override fun getAll(): List<Task> {
        val tasks = mutableListOf<Task>()
        for (json in sharedPreferences.all.values) {
            json?.apply { tasks.add(fromJson(this)) }
        }
        return tasks
    }

    private fun fromJson(json: Any): Task {
        return gson.fromJson(json.toString(), DbTask::class.java).let { taskFactory.from(it) }
    }

    override fun set(task: Task): Task {
        return task.also { sharedPreferences.edit().putString(task.id.toString(), gson.toJson(task.deflate())).apply() }
    }

    override fun delete(task: Task) {
        sharedPreferences.edit().remove(task.id.toString()).apply()
    }
}