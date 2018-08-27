package com.davidcryer.todo.common

import com.davidcryer.todo.add.TaskSubmission
import java.util.*

class Task(val id: UUID, val title: String) {

    fun deflate(): DbTask {
        return DbTask(id, title)
    }

    companion object {

        fun from(submission: TaskSubmission): Task {
            gateKeepValidTask(submission)
            return Task(UUID.randomUUID(), submission.title)
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
}