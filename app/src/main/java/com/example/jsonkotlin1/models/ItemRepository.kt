package com.example.jsonkotlin1.models

class ItemRepository private constructor (private val itemDao: FakeItemDao) {

    fun getItems() = itemDao.getItems()

    companion object {
        @Volatile private var instance: ItemRepository? = null

        fun getInstance(itemDao: FakeItemDao) =
            instance ?: synchronized(this) {
                instance ?: ItemRepository(itemDao).also { instance = it }
            }

    }
}