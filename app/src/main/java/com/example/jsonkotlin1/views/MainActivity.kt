package com.example.jsonkotlin1.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.jsonkotlin1.R
import com.example.jsonkotlin1.data.db.entity.Item
import com.example.jsonkotlin1.viewmodels.ItemViewModel
import com.example.jsonkotlin1.viewmodels.ItemViewModelFactory
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.generic.instance

class MainActivity : AppCompatActivity(), KodeinAware {

    override val kodein by closestKodein()
    private val viewModelFactory: ItemViewModelFactory by instance()

    private val items = mutableListOf<Item>()

    private lateinit var viewModel: ItemViewModel
    private lateinit var recyclerview: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // getting the recyclerview by its id
        recyclerview = findViewById(R.id.recyclerview)

        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(ItemViewModel::class.java)

        // this creates a vertical layout Manager
        recyclerview.layoutManager = LinearLayoutManager(this)
        
        val adapter = viewModel.getItems().value?.let { CustomAdapter(it) }

        // Setting the Adapter with the recyclerview
        recyclerview.adapter = adapter

    }

}