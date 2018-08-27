package com.davidcryer.todo.add

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.MotionEvent
import android.view.View
import android.widget.LinearLayout
import com.davidcryer.todo.R
import com.davidcryer.utils.SimpleTextWatcher
import kotlinx.android.synthetic.main.layout_add_task.view.*

class AddTaskView(context: Context) : LinearLayout(context) {
    private val watcher = object : SimpleTextWatcher() {
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) { titleError(null) }
    }

    init {
        View.inflate(context, R.layout.layout_add_task, this)
        setUpTitleTouchListener()
    }

    private fun setUpTitleTouchListener() {
        title.setOnTouchListener { _, e ->
            when (e.actionIndex) {
                MotionEvent.ACTION_DOWN -> {
                    titleError(null)
                }
            }
            false
        }
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        title.addTextChangedListener(watcher)
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        title.removeTextChangedListener(watcher)
    }

    fun titleError(e: String?) {
        titleLayout.error = e
    }

    fun values(): TaskSubmission {
        return TaskSubmission(title.text.toString())
    }
}