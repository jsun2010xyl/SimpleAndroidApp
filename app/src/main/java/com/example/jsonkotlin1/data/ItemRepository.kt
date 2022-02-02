package com.example.jsonkotlin1.data

import androidx.lifecycle.MutableLiveData
import com.example.jsonkotlin1.data.db.entity.Item

class ItemRepository() {
    // 这个是程序现在正在用的，改了后不用它
    fun getItems() : MutableLiveData<List<Item>>{
        val data = MutableLiveData<List<Item>>()
        setItems2()
        data.value = items
        return data
    }

    private fun setItems2(){
        val item = Item(1, 1, "name1")
        items.add(item)
    }

    private val items = mutableListOf<Item>()

    // fun getItems() = itemDao.getItems()

    companion object {
        @Volatile private var instance: ItemRepository? = null

        fun getInstance() =
            instance ?: synchronized(this) {
                instance ?: ItemRepository().also { instance = it }
            }

    }
}