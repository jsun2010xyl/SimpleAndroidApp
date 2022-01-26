package com.example.jsonkotlin1.data.db

class FakeDatabase private constructor() {

    var itemDao = FakeItemDao()
        private set

    companion object {
        @Volatile private var instance: FakeDatabase? = null

        fun getInstance() =
            instance ?: synchronized(this) {
                instance ?: FakeDatabase().also { instance = it }
            }

    }
}