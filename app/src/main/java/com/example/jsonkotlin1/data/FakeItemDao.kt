package com.example.jsonkotlin1.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class FakeItemDao {
    private val itemList = mutableListOf<Item>()
    private val items = MutableLiveData<List<Item>>()

    init {
        items.value = itemList
    }

    fun getItems() = items as LiveData<List<Item>>
}