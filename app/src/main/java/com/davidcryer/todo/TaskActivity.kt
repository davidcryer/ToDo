package com.davidcryer.todo

import android.support.v7.app.ActionBar
import com.davidcryer.simpleactivities.SimpleAppBarActivity
import com.davidcryer.todo.add.AddTaskFragment
import com.davidcryer.todo.list.TasksFragment

class TaskActivity : SimpleAppBarActivity(), TasksFragment.Navigator {
    private val FRAGMENT_LIST = "list"
    private val FRAGMENT_ADD = "add"

    override fun addInitialFragment() {
        add(FRAGMENT_LIST) { TasksFragment() }
    }

    override fun setupActionBar(actionBar: ActionBar) {

    }

    override fun openAddTask() {
        if (!hasFragment(FRAGMENT_ADD)) {
            AddTaskFragment().show(supportFragmentManager, FRAGMENT_ADD)
        }
    }
}