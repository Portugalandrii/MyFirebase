package com.example.myfirebase

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

//@Parcelize
//data class User(val key: String, val name: String, val age: String) : Parcelable
class User() {
    var key: String = ""
    var name: String = ""
    var age: String = ""


    constructor(key: String, name: String, age: String) : this() {
        this.key = key
        this.name = name
        this.age = age
    }

    constructor(name: String, age: String) : this() {
        this.name = name
        this.age = age
    }

}
