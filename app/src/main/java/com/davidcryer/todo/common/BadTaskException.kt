package com.davidcryer.todo.common

class BadTaskException(val title: String?) : Exception(message(title)) {

    companion object {

        fun message(title: String?): String {
            var message = "Error creating new Task:\n"
            message += title.let { "\tTitle must not be null\n" }
            return message
        }
    }
}