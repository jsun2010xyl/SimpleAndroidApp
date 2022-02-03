package com.example.jsonkotlin1.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.jsonkotlin1.R
import com.example.jsonkotlin1.data.db.entity.Item
import com.example.jsonkotlin1.ui.base.ScopedActivity
import com.example.jsonkotlin1.viewmodels.ItemViewModel
import com.example.jsonkotlin1.viewmodels.ItemViewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.generic.instance

class MainActivity : ScopedActivity(), KodeinAware {

    override val kodein by closestKodein()
    private val viewModelFactory: ItemViewModelFactory by instance()

    private lateinit var viewModel: ItemViewModel
    private lateinit var recyclerview: RecyclerView
    private lateinit var adapter: CustomAdapter

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(ItemViewModel::class.java)



        bindUI(this)

    }

    private fun bindUI(context: Context) = launch(Dispatchers.Main) {
        val itemList = viewModel.itemList.await()
        itemList.observe(this@MainActivity, Observer {
            if (it == null) {
                //Log.i("Msg", "it == null")
                return@Observer
            }
            //Log.i("Msg",it.get(0).toString())

            // getting the recyclerview by its id
            recyclerview = findViewById(R.id.recyclerview)
            adapter = CustomAdapter(it)
            // Setting the Adapter with the recyclerview
            recyclerview.adapter = adapter
            // this creates a vertical layout Manager
            recyclerview.layoutManager = LinearLayoutManager(context)
        })
    }

}