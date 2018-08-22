package com.davidcryer.todo.common

import org.junit.Test
import java.util.*

class TaskTests {

    @Test
    fun create() {
        val id = UUID.randomUUID()
        val title = "title"
        val task = Task(id, title)
        assert(task.id == id)
        assert(task.title == title)
    }
}