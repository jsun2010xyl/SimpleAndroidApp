package com.example.jsonkotlin1.viewmodels

import androidx.lifecycle.ViewModel
import com.example.jsonkotlin1.data.ItemRepository

// ViewModel存在的目的就是减少coupling
class ItemViewModel (private val itemRepository: ItemRepository)
    : ViewModel() {

    fun getItems() = itemRepository.getItems()

}