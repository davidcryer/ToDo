package com.davidcryer.todo.add

import android.os.Parcel
import android.os.Parcelable
import com.davidc.uiwrapper.UiModel

class AddTaskModel() : UiModel<AddTaskUi> {

    constructor(parcel: Parcel) : this()

    override fun onto(ui: AddTaskUi) {

    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<AddTaskModel> {
        override fun createFromParcel(parcel: Parcel): AddTaskModel { return AddTaskModel(parcel) }
        override fun newArray(size: Int): Array<AddTaskModel?> { return arrayOfNulls(size) }
    }
}