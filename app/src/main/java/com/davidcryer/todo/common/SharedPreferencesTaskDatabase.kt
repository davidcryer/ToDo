package com.davidcryer.todo.common

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import java.util.*

class SharedPreferencesTaskDatabase(
        private val sharedPreferences: SharedPreferences,
        private val gson: Gson,
        private val taskFactory: TaskFactory
) : TaskDatabase {
    companion object {
        private const val PREFS_KEY = "tasks"

        fun create(context: Context, gson: Gson, taskFactory: TaskFactory): SharedPreferencesTaskDatabase {
            val sharedPreferences = context.getSharedPreferences(PREFS_KEY, Context.MODE_PRIVATE)
            return SharedPreferencesTaskDatabase(sharedPreferences, gson, taskFactory)
        }
    }

    override fun get(id: UUID): Task? {
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

    override fun insert(submission: TaskSubmission): Task {
        return taskFactory.from(submission).also {
            sharedPreferences.edit().putString(it.id.toString(), gson.toJson(it.deflate())).apply()
        }

    }

    override fun update(task: Task) {
        sharedPreferences.edit().putString(task.id.toString(), gson.toJson(task.deflate())).apply()
    }

    override fun delete(task: Task) {
        sharedPreferences.edit().remove(task.id.toString()).apply()
    }
}