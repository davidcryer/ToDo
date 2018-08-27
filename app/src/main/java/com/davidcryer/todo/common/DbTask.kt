package com.davidcryer.todo.common

import android.os.Parcel
import android.os.Parcelable
import java.util.*

class DbTask(val id: UUID, val title: String) : Parcelable {

    constructor(parcel: Parcel) : this(UUID.fromString(parcel.readString()), parcel.readString())

    fun inflate(): Task {
        return Task(id, title)
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id.toString())
        parcel.writeString(title)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<DbTask> {
        override fun createFromParcel(parcel: Parcel): DbTask { return DbTask(parcel) }
        override fun newArray(size: Int): Array<DbTask?> { return arrayOfNulls(size) }
    }
}