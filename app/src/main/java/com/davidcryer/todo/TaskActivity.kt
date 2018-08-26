package com.davidcryer.todo

import android.support.v7.app.ActionBar
import com.davidcryer.simpleactivities.SimpleAppBarActivity
import com.davidcryer.todo.list.TasksFragment

class TaskActivity : SimpleAppBarActivity() {
    private val FRAGMENT_LIST = "list"

    override fun addInitialFragment() {
        add(FRAGMENT_LIST) { TasksFragment() }
    }

    override fun setupActionBar(actionBar: ActionBar) {

    }
}