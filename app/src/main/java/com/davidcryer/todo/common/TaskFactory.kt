package com.davidcryer.todo.common

import java.util.*

class TaskFactory {

    fun create(id: UUID, title: String, done: Boolean): Task {
        return Task(id, title, done)
    }

    fun from(task: DbTask): Task {
        return create(task.id, task.title, task.done)
    }

    fun from(submission: TaskSubmission): Task {
        gateKeepValidTask(submission)
        return create(UUID.randomUUID(), submission.title, done = false)
    }

    @Throws(BadTaskException::class)
    private fun gateKeepValidTask(submission: TaskSubmission) {
        var error = false
        val badTitle = if (submission.title.isEmpty()) { error = true; "Title must not be empty" } else null
        if (error) {
            throw BadTaskException(badTitle)
        }
    }
}