package com.davidcryer.todo.common

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import java.util.*

class SharedPreferencesTaskStore(
        private val sharedPreferences: SharedPreferences,
        private val gson: Gson
) : TaskStore {
    companion object {
        private val PREFS_KEY = "tasks"

        fun create(context: Context, gson: Gson): SharedPreferencesTaskStore {
            val sharedPreferences = context.getSharedPreferences(PREFS_KEY, Context.MODE_PRIVATE)
            return SharedPreferencesTaskStore(sharedPreferences, gson)
        }
    }

    override fun get(id: UUID): Task? {//TODO optimise (probably with cache)
        val tasks = getAll()
        for (task in tasks) {
            if (task.id == id) {
                return task
            }
        }
        return null
    }

    override fun getAll(): List<Task> {
        val tasks = mutableListOf<Task>()
        for (json in sharedPreferences.all.values) {
            json?.apply { tasks.add(fromJson(this)) }
        }
        return tasks
    }

    private fun fromJson(json: Any): Task {
        return gson.fromJson(json.toString(), DbTask::class.java).inflate()
    }

    override fun set(task: Task): Task {
        return task.also { sharedPreferences.edit().putString(task.toString(), gson.toJson(task.deflate())).apply() }
    }

    override fun delete(task: Task) {
        sharedPreferences.edit().remove(task.id.toString()).apply()
    }
}