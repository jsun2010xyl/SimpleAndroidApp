package com.example.jsonkotlin1.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.jsonkotlin1.R
import com.example.jsonkotlin1.models.Item
import java.util.concurrent.CountDownLatch

class MainActivity : AppCompatActivity() {
    // Use items to store the data from the json file
    private val items = mutableListOf<Item>()
    private val latch = CountDownLatch(1)

    /*
    fun getData(){
        // Start a new thread because we cannot connect to the Internet on the main thread
        thread(start=true) {
            // Use try-catch to handle exceptions
            try {
                // Get the json file from aws
                val connection = URL("https://fetch-hiring.s3.amazonaws.com/hiring.json").openConnection() as HttpURLConnection
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
                latch.countDown()

            }catch(e: Exception){
                // Record the exception information
                Log.i("Exception msg",e.toString())
            }
        }
    }*/

    override fun onCreate(savedInstanceState: Bundle?) {

        //getData()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        // getting the recyclerview by its id
        val recyclerview = findViewById<RecyclerView>(R.id.recyclerview)

        // this creates a vertical layout Manager
        recyclerview.layoutManager = LinearLayoutManager(this)

        //latch.await()
        // This will pass the List to our Adapter
        val adapter = CustomAdapter(items)
        // Setting the Adapter with the recyclerview
        recyclerview.adapter = adapter

    }

}