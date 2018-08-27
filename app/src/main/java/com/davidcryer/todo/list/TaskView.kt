package com.davidcryer.todo.list

import android.content.Context
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import com.davidcryer.todo.R
import kotlinx.android.synthetic.main.item_task.view.*

class TaskView(context: Context, attrs: AttributeSet) : FrameLayout(context, attrs) {

    init {
        View.inflate(context, R.layout.item_task, this)
    }

    fun setTitle(title: String) {
        this.title.text = title
    }

    fun setDone(done: Boolean) {
        if (done) {
            title.paintFlags = title.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        } else {
            title.paintFlags = title.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
        }
    }
}