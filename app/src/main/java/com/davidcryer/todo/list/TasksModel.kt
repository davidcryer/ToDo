package com.davidcryer.todo.list

import android.os.Parcel
import android.os.Parcelable
import com.davidc.uiwrapper.UiModel

class TasksModel() : UiModel<TasksUi> {

    constructor(source: Parcel): this()

    companion object {
        @JvmField val CREATOR: Parcelable.Creator<TasksModel> = object : Parcelable.Creator<TasksModel> {
            override fun createFromParcel(source: Parcel): TasksModel{
                return TasksModel(source)
            }

            override fun newArray(size: Int): Array<TasksModel?> {
                return arrayOfNulls(size)
            }
        }
    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onto(ui: TasksUi) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun describeContents(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}