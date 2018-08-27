package com.davidcryer.todo.add

import android.app.Dialog
import android.os.Bundle
import android.support.v7.app.AlertDialog
import com.davidc.uiwrapper.UiWrapper
import com.davidc.uiwrapper.UiWrapperFactoryDialogFragment
import com.davidcryer.todo.R
import com.davidcryer.todo.UiWrapperFactory

class AddTaskFragment : UiWrapperFactoryDialogFragment<AddTaskUi, AddTaskUi.Listener, UiWrapperFactory>() {
    private var layout: AddTaskView? = null
    private val ui = object : AddTaskUi {
        override fun showError(title: String) { layout?.titleError(title) }
        override fun dismiss() { dialog?.dismiss() }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val c = context!!
        layout = AddTaskView(c)
        val d = AlertDialog.Builder(c)
                .setTitle(R.string.add_task_title)
                .setView(layout)
                .setPositiveButton(R.string.add_task_create, null)
                .setNegativeButton(R.string.add_task_cancel) { _, _ -> }
                .create()
        d.setOnShowListener { _ -> d.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener { _ -> onClickSubmit() }}
        d.show()
        return d
    }

    private fun onClickSubmit() {
        layout?.let { listener().onClickSubmit(ui(), it.values()) }
    }

    override fun uiWrapper(uiWrapperFactory: UiWrapperFactory, savedState: Bundle?): UiWrapper<AddTaskUi, AddTaskUi.Listener, *> {
        return uiWrapperFactory.createAddTask(savedState)
    }

    override fun ui(): AddTaskUi {
        return ui
    }
}