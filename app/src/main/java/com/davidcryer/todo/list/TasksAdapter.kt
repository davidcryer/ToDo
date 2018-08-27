package com.davidcryer.todo.list

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.davidcryer.todo.R

class TasksAdapter : RecyclerView.Adapter<TasksAdapter.ViewHolder>() {
    private var tasks: List<UiTask> = ArrayList()

    fun tasks(tasks: List<UiTask>) {
        this.tasks = tasks
        notifyDataSetChanged()
    }

    fun append(task: UiTask) {
        tasks += task
        notifyItemInserted(itemCount - 1)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TasksAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_task, parent, false)
        return ViewHolder(view as TaskView)
    }

    override fun getItemCount(): Int {
        return tasks.size
    }

    override fun onBindViewHolder(holder: TasksAdapter.ViewHolder, position: Int) {
        holder.bind(tasks[position])
    }

    class ViewHolder(private val v: TaskView) : RecyclerView.ViewHolder(v) {

        fun bind(task: UiTask) {
            v.setTitle(task.title)
        }
    }
}