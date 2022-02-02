package com.example.jsonkotlin1.data.repository

import androidx.lifecycle.LiveData
import com.example.jsonkotlin1.data.ItemList
import com.example.jsonkotlin1.data.db.entity.Item

interface ItemListRepository {
    //                             TODO : 注意这里是List<Item>
    suspend fun getItemList(): LiveData<List<Item>>
}