package com.example.jsonkotlin1.data

import androidx.lifecycle.MutableLiveData
import com.example.jsonkotlin1.data.db.entity.Item
import org.json.JSONArray
import org.json.JSONTokener
import java.net.HttpURLConnection
import java.net.URL

class ItemRepository() {

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