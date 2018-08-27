package com.davidcryer.todo.add

import android.content.Context
import android.view.View
import android.widget.LinearLayout
import com.davidcryer.todo.R
import kotlinx.android.synthetic.main.layout_add_task.view.*

class AddTaskView(context: Context) : LinearLayout(context) {

    init {
        View.inflate(context, R.layout.layout_add_task, this)
        title.onFocusChangeListener = OnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                title.error = null
            }
        }
    }

    fun titleError(e: String?) {
        title.error = e
    }

    fun values(): TaskSubmission {
        return TaskSubmission(title.text.toString())
    }
}