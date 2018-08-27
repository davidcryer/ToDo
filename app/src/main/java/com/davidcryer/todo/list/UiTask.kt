package com.davidcryer.todo.list

import android.os.Parcel
import android.os.Parcelable
import com.davidcryer.todo.common.Task
import com.davidcryer.todo.common.TaskListener
import com.davidcryer.todo.common.TaskManager
import java.util.*

class UiTask(private val id: UUID, val title: String) : TaskListener, Parcelable {

    constructor(task: Task) : this(task.id, task.title)

    constructor(parcel: Parcel) : this(
            UUID.fromString(parcel.readString()),
            parcel.readString()
    )

    fun attach(taskManager: TaskManager) {
        taskManager.attach(id, this)
    }

    fun detach(taskManager: TaskManager) {
        taskManager.detach(id, this)
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id.toString())
        parcel.writeString(title)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<UiTask> {
        override fun createFromParcel(parcel: Parcel): UiTask { return UiTask(parcel) }
        override fun newArray(size: Int): Array<UiTask?> { return arrayOfNulls(size) }
    }
}