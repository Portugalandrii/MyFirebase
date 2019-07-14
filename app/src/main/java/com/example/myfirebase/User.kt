package com.example.myfirebase

class User {
    var key: String = ""
    var name: String = ""
    var age: String = ""

    constructor() {}
    constructor(key: String, name: String, age: String) {
        this.key = key
        this.name = name
        this.age = age
    }

    constructor(name: String, age: String) {
        this.name = name
        this.age = age
    }

}
