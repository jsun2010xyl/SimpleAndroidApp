package com.example.jsonkotlin1.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.jsonkotlin1.data.ItemRepository

class ItemViewModelFactory(private val itemRepository: ItemRepository)
    : ViewModelProvider.Factory {


    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ItemViewModel(itemRepository) as T
    }



}