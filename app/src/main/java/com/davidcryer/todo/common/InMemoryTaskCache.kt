package com.davidcryer.todo.common

import com.davidcryer.utils.MapUtils
import java.util.*

class InMemoryTaskCache : TaskCache {
    private val tasks = mutableMapOf<UUID, Task?>()

    override fun get(id: UUID, taskLoader: () -> Task?): Task? {
        return MapUtils.getOptValue(tasks, id, taskLoader)
    }

    override fun set(task: Task) {
        tasks[task.id] = task
    }

    override fun setAll(tasks: Collection<Task>) {
        this.tasks.clear().also { _ -> tasks.forEach { set(it) } }
    }

    override fun remove(task: Task) {
        tasks -= task.id
    }
}