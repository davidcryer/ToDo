package com.davidcryer.todo.list

import android.os.Parcel
import android.os.Parcelable
import com.davidc.uiwrapper.UiModel

class TasksModel(private val tasks: List<UiTask>) : UiModel<TasksUi> {

    constructor(parcel: Parcel) : this(parcel.createTypedArrayList(UiTask.CREATOR))

    override fun onto(ui: TasksUi) {
        ui.show(tasks)
    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeTypedList(tasks)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object {
        @JvmField val CREATOR = object : Parcelable.Creator<TasksModel> {
            override fun createFromParcel(source: Parcel): TasksModel{ return TasksModel(source) }
            override fun newArray(size: Int): Array<TasksModel?> { return arrayOfNulls(size) }
        }
    }
}