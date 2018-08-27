package com.davidcryer.todo.common

import android.os.Parcel
import android.os.Parcelable
import java.util.*

class DbTask(val id: UUID, val title: String, val done: Boolean) : Parcelable {

    constructor(parcel: Parcel) : this(
            id = UUID.fromString(parcel.readString()),
            title = parcel.readString(),
            done = parcel.readByte() != 0.toByte()
    )

    fun inflate(): Task {
        return Task(id, title, done)
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id.toString())
        parcel.writeString(title)
        parcel.writeByte(if (done) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<DbTask> {
        override fun createFromParcel(parcel: Parcel): DbTask { return DbTask(parcel) }
        override fun newArray(size: Int): Array<DbTask?> { return arrayOfNulls(size) }
    }
}