package com.example.jsonkotlin1.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.jsonkotlin1.R
import com.example.jsonkotlin1.data.db.entity.Item
import com.example.jsonkotlin1.utilities.InjectorUtils
import com.example.jsonkotlin1.viewmodels.ItemViewModel

class MainActivity : AppCompatActivity() {


    private val items = mutableListOf<Item>()

    private lateinit var viewModel: ItemViewModel
    private lateinit var recyclerview: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // getting the recyclerview by its id
        recyclerview = findViewById(R.id.recyclerview)

        val factory = InjectorUtils.provideItemViewModelFactory()
        viewModel = ViewModelProvider(this, factory).get(ItemViewModel::class.java)

        // this creates a vertical layout Manager
        recyclerview.layoutManager = LinearLayoutManager(this)

        //Latch.latch1.await()
        val adapter = viewModel.getItems().value?.let { CustomAdapter(it) }
        // Setting the Adapter with the recyclerview
        recyclerview.adapter = adapter

    }

}