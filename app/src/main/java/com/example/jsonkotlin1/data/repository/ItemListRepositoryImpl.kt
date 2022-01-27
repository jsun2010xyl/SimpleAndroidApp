package com.example.jsonkotlin1.data.repository

import androidx.lifecycle.LiveData
import com.example.jsonkotlin1.data.ItemList
import com.example.jsonkotlin1.data.db.ItemDao
import com.example.jsonkotlin1.data.network.NetworkDataSource

class ItemListRepositoryImpl(
    private val itemDao : ItemDao,
    private val itemNetworkDataSource : NetworkDataSource
) : ItemListRepository {
    override suspend fun getItemList(): LiveData<ItemList> {
        TODO("Not yet implemented")
    }
}