package com.example.jsonkotlin1.data.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.jsonkotlin1.data.ItemList
import java.lang.Exception

class NetworkDataSourceImpl(private val apiService: ItemApiService) : NetworkDataSource {
    private val _downloadedData = MutableLiveData<ItemList>()
    override val downloadedData: LiveData<ItemList>
        get() = _downloadedData

    override suspend fun fetchData() {
        try {
            val fetchedData = apiService.getItemList().await()
            _downloadedData.postValue(fetchedData)
        }catch(e: Exception){ // TODO
            Log.e("Connectivity", "No Internet connection.", e)
        }
    }
}