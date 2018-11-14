package com.davidcryer.todo.list

import android.os.Parcel
import android.os.Parcelable
import com.davidc.uiwrapper.UiModel

class TasksModel(var tasks: MutableList<UiTask>) : UiModel {

    constructor(parcel: Parcel) : this(parcel.createTypedArrayList(UiTask.CREATOR))

    fun set(tasks: MutableList<UiTask>) {
        this.tasks = tasks
    }

    fun append(ui: TasksUi?, task: UiTask) {
        ui?.let { ui.append(task) }
        tasks.add(task)
    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeTypedList(tasks)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<TasksModel> {
        override fun createFromParcel(source: Parcel): TasksModel{ return TasksModel(source) }
        override fun newArray(size: Int): Array<TasksModel?> { return arrayOfNulls(size) }
    }
}