package com.example.jsonkotlin1.data.network

import androidx.lifecycle.LiveData
import com.example.jsonkotlin1.data.ItemList

interface NetworkDataSource {
    val downloadedData: LiveData<ItemList>

    suspend fun fetchData(

    )
}