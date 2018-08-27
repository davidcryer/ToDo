package com.davidcryer.todo.list

import android.os.Parcel
import android.os.Parcelable
import com.davidcryer.todo.common.Task
import com.davidcryer.todo.common.TaskListener
import com.davidcryer.todo.common.TaskManager
import java.util.*

class UiTask(private val id: UUID, val title: String, var done: Boolean) : TaskListener, Parcelable {
    private var taskManager: TaskManager? = null
    private var view: TaskView? = null

    constructor(task: Task) : this(task.id, task.title, task.done)

    constructor(parcel: Parcel) : this(
            id = UUID.fromString(parcel.readString()),
            title = parcel.readString(),
            done = parcel.readByte() != 0.toByte()
    )

    fun attach(taskManager: TaskManager) {
        this.taskManager = taskManager.also{ it.get(id).attach(listener = this) }
    }

    fun detach(taskManager: TaskManager) {
        taskManager.get(id).detach(this).also { this.taskManager = null }
    }

    fun attach(view: TaskView) {
        this.view = view.also { _ ->
            view.setTitle(title)
            view.setDone(done)
            view.setOnClickListener { v -> taskManager?.get(id)?.toggleDone(ignore = this) { view.setDone(it) } }
        }
    }

    fun detach(view: TaskView) {
        view.setOnClickListener(null).also { this.view = null }
    }

    override fun onChangeDone(done: Boolean) {
        view?.setDone(done)
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id.toString())
        parcel.writeString(title)
        parcel.writeByte(if (done) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<UiTask> {
        override fun createFromParcel(parcel: Parcel): UiTask { return UiTask(parcel) }
        override fun newArray(size: Int): Array<UiTask?> { return arrayOfNulls(size) }
    }
}