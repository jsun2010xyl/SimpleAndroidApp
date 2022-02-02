package com.example.jsonkotlin1.data.repository

import androidx.lifecycle.LiveData
import com.example.jsonkotlin1.data.ItemList
import com.example.jsonkotlin1.data.db.ItemDao
import com.example.jsonkotlin1.data.network.NetworkDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ItemListRepositoryImpl(
    private val itemDao : ItemDao,
    private val itemNetworkDataSource : NetworkDataSource
) : ItemListRepository {

    init{
        itemNetworkDataSource.downloadedData.observeForever { newCurrentItemList ->
            // persist
        }
    }

    override suspend fun getItemList(): LiveData<ItemList> {
        TODO("Not yet implemented")
    }

    //                TODO : 这里应该是ItemList还是List<Item>，二者有没区别？
    private fun persistFetchedCurrentItemList(fetchedItemList: ItemList){
        GlobalScope.launch(Dispatchers.IO) {
            itemDao.upsert(fetchedItemList)
        }

    }
}