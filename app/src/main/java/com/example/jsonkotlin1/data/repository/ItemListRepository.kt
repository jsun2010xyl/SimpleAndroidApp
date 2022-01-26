package com.example.jsonkotlin1.data.repository

import androidx.lifecycle.LiveData
import com.example.jsonkotlin1.data.ItemList

interface ItemListRepository {
    suspend fun getItemList(): LiveData<ItemList>
}