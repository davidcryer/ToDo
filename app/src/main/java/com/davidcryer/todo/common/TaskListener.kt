package com.davidcryer.todo.common

import java.time.Instant

interface TaskListener {
    fun onChangeDone(done: Boolean, timestamp: Instant)
}