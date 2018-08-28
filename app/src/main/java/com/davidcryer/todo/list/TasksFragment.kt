package com.davidcryer.todo.list

import android.content.Context
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.*
import com.davidc.uiwrapper.UiWrapper
import com.davidc.uiwrapper.UiWrapperFactoryFragment
import com.davidcryer.todo.R
import com.davidcryer.todo.UiWrapperFactory
import kotlinx.android.synthetic.main.fragment_tasks.view.*

class TasksFragment : UiWrapperFactoryFragment<TasksUi, TasksUi.Listener, UiWrapperFactory>() {
    private val tasksAdapter = TasksAdapter()
    private var navigator: Navigator? = null
    private val ui = object : TasksUi {
        override fun set(tasks: List<UiTask>) { tasksAdapter.tasks(tasks) }
        override fun append(task: UiTask) {
            tasksAdapter.append(task)
        }

        override fun openAddTask() { navigator?.openAddTask() }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        navigator = context as Navigator
    }

    override fun onDetach() {
        super.onDetach()
        navigator = null
    }

    override fun onCreate(savedState: Bundle?) {
        super.onCreate(savedState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_tasks, container, false)
        setUp(view)
        return view
    }

    private fun setUp(view: View) {
        view.list.layoutManager = LinearLayoutManager(view.context)
        view.list.adapter = tasksAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.tasks, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.add_task -> {
                listener().onClickAddTask(ui())
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onStart() {
        super.onStart()
        activity?.title = "To-Do's"
    }

    override fun uiWrapper(uiWrapperFactory: UiWrapperFactory, savedState: Bundle?): UiWrapper<TasksUi, TasksUi.Listener, *> {
        return uiWrapperFactory.createTasks(savedState)
    }

    override fun ui(): TasksUi {
        return ui
    }

    interface Navigator {
        fun openAddTask()
    }
}