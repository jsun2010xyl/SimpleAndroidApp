package com.example.jsonkotlin1.data.db

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.jsonkotlin1.data.Item

class FakeItemDao {
    private val itemList = mutableListOf<Item>()
    private val items = MutableLiveData<List<Item>>()

    init {
        items.value = itemList
    }

    fun getItems() = items as LiveData<List<Item>>
}