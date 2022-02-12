package com.example.jsonkotlin1.viewmodels

import androidx.lifecycle.ViewModel
import com.example.jsonkotlin1.data.repository.ItemListRepository
import com.example.jsonkotlin1.internal.lazyDeferred

class ItemViewModel (private val itemListRepository: ItemListRepository)
    : ViewModel() {

    val itemList by lazyDeferred {
        itemListRepository.getItemList()
    }
}