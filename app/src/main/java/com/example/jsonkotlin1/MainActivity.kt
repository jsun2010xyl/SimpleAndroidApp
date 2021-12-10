package com.example.jsonkotlin1

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONArray
import org.json.JSONTokener
import java.net.HttpURLConnection
import java.net.URL
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    // Use items to store the data from the json file
    private val items = mutableListOf<Item>()

    override fun onCreate(savedInstanceState: Bundle?) {
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

            }catch(e: Exception){
                // Record the exception information
                Log.i("Exception msg",e.toString())
            }
        }

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // getting the recyclerview by its id
        val recyclerview = findViewById<RecyclerView>(R.id.recyclerview)

        // this creates a vertical layout Manager
        recyclerview.layoutManager = LinearLayoutManager(this)

        if ( items == null ){
            // If an exception happens
            val text = "Something is wrong. There is an exception happened."
            val duration = Toast.LENGTH_SHORT
            // Show a toast message
            val toast = Toast.makeText(applicationContext, text, duration)
            toast.show()

        }else{
            // Wait until we get the data from the json file on aws
            while (true){
                if (items.size>0){
                    // This will pass the List to our Adapter
                    val adapter = CustomAdapter(items)
                    // Setting the Adapter with the recyclerview
                    recyclerview.adapter = adapter
                    break
                }
            }
        }
    }
}