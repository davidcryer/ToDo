package com.davidcryer.todo.list

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.davidc.uiwrapper.UiWrapper
import com.davidc.uiwrapper.UiWrapperFactoryFragment
import com.davidcryer.todo.R
import com.davidcryer.todo.UiWrapperFactory
import kotlinx.android.synthetic.main.fragment_tasks.view.*

class TasksFragment : UiWrapperFactoryFragment<TasksUi, TasksUi.Listener, UiWrapperFactory>() {
    private val tasksAdapter = TasksAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_tasks, container, false)
        setUp(view)
        return view
    }

    private fun setUp(view: View) {
        view.list.layoutManager = LinearLayoutManager(view.context)
        view.list.adapter = tasksAdapter
    }

    override fun onStart() {
        super.onStart()
        activity?.title = "To-Do's"
    }

    override fun uiWrapper(uiWrapperFactory: UiWrapperFactory, savedState: Bundle?): UiWrapper<TasksUi, TasksUi.Listener, *> {
        return uiWrapperFactory.createTasks(savedState)
    }

    override fun ui(): TasksUi {
        return object: TasksUi {
            override fun show(tasks: List<UiTask>) {
                tasksAdapter.tasks(tasks)
            }
        }
    }
}