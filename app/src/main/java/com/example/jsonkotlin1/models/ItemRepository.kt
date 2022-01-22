package com.example.jsonkotlin1.models

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.jsonkotlin1.utilities.Latch
import org.json.JSONArray
import org.json.JSONTokener
import java.net.HttpURLConnection
import java.net.URL
import kotlin.concurrent.thread

class ItemRepository() {

    fun getItems() : MutableLiveData<List<Item>>{
        val data = MutableLiveData<List<Item>>()
        setItems()
        data.value = items
        return data
    }

    private fun setItems2(){
        val item = Item(1, 1, "name1")
        items.add(item)
    }

    // Retrieve the data from aws
    private fun setItems(){
        // TODO : 这里有问题，无法显示数据，只有空白
        // TODO : 不能联网或者不能找到这个文件怎么办？
        thread (start = true){
            //Latch.latch1.countDown()
            try {
                // Get the json file from aws
                val connection =
                    URL("https://fetch-hiring.s3.amazonaws.com/hiring.json").openConnection() as HttpURLConnection
                val data = connection.inputStream.bufferedReader().readText()
                // Parse the json file
                val jsonArray = JSONTokener(data).nextValue() as JSONArray
                // Store the data in the list "items"
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
                            // add the item to the list
                            items.add(item)
                        }
                    }
                }

                // sort
                val c1 = compareBy<Item> { it.listId }
                val c2 = c1.thenBy { it.name }
                items.sortWith(c2)

            }catch(e: Exception){
                // Record the exception information
                Log.i("Exception msg",e.toString())
            }


        }
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