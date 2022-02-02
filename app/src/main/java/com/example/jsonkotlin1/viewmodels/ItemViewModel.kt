package com.example.jsonkotlin1.viewmodels

import androidx.lifecycle.ViewModel
import com.example.jsonkotlin1.data.repository.ItemListRepository
import com.example.jsonkotlin1.internal.lazyDeferred

// ViewModel存在的目的就是减少coupling
// class ItemViewModel (private val itemRepository: ItemRepository)
class ItemViewModel (private val itemListRepository: ItemListRepository)
    : ViewModel() {

    // fun getItems() = itemRepository.getItems()
    val itemList by lazyDeferred {
        itemListRepository.getItemList()
    }
}