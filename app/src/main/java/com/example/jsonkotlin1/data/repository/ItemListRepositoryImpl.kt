package com.example.jsonkotlin1.data.repository

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import com.example.jsonkotlin1.data.ItemList
import com.example.jsonkotlin1.data.db.ItemDao
import com.example.jsonkotlin1.data.db.entity.Item
import com.example.jsonkotlin1.data.network.NetworkDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.ZonedDateTime

class ItemListRepositoryImpl(
    private val itemDao : ItemDao,
    private val itemNetworkDataSource : NetworkDataSource
) : ItemListRepository {

    init{
        itemNetworkDataSource.downloadedData.observeForever { newCurrentItemList ->
            persistFetchedCurrentItemList(newCurrentItemList)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun getItemList(): LiveData<List<Item>> {
        return withContext(Dispatchers.IO){
            initItemList()
            return@withContext itemDao.getItems()
        }
    }

    private fun persistFetchedCurrentItemList(fetchedItemList: ItemList){
        GlobalScope.launch(Dispatchers.IO) {
            // fetchedItemList是ItemList，参数是List<Item>
            // 可以从Arraylist到List
            itemDao.upsert(fetchedItemList)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private suspend fun initItemList(){
        // TODO : if (true)
        if (true){
            fetchItemList()
            //Log.i("Msg", "Downloaded data from aws.")
        }
    }

    private suspend fun fetchItemList(){
        itemNetworkDataSource.fetchData()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun isFetchedItemListNeeded(lastFetchTime: ZonedDateTime): Boolean{
        val twoHrsAgo = ZonedDateTime.now().minusMinutes(120)
        return lastFetchTime.isBefore(twoHrsAgo)
    }
}