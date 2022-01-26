package com.example.jsonkotlin1.data

import android.util.Log
import androidx.lifecycle.MutableLiveData
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

    private fun setItems(){
        // TODO

                val connection =
                    URL("https://fetch-hiring.s3.amazonaws.com/hiring.json").openConnection() as HttpURLConnection
                val data = connection.inputStream.bufferedReader().readText()

                val jsonArray = JSONTokener(data).nextValue() as JSONArray

                for (i in 0 until jsonArray.length()) {
                    // filter out those whose name is null
                    if (!jsonArray.getJSONObject(i).isNull("name")) {
                        val name: String? = jsonArray.getJSONObject(i).getString("name")
                        // filter out those whose name is empty
                        if (name != "") {
                            val item = Item(
                                jsonArray.getJSONObject(i).getInt("id"),
                                jsonArray.getJSONObject(i).getInt("listId"),
                                jsonArray.getJSONObject(i).getString("name")
                            )

                            items.add(item)
                        }
                    }
                }

                // sort
                val c1 = compareBy<Item> { it.listId }
                val c2 = c1.thenBy { it.name }
                items.sortWith(c2)



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