package com.example.jsonkotlin1.data.repository

import androidx.lifecycle.LiveData
import com.example.jsonkotlin1.data.db.entity.Item

interface ItemListRepository {
    suspend fun getItemList(): LiveData<List<Item>>
}