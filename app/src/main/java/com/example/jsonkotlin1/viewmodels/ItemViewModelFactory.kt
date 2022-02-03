package com.example.jsonkotlin1.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.jsonkotlin1.data.repository.ItemListRepository

class ItemViewModelFactory(private val itemListRepository: ItemListRepository)
    : ViewModelProvider.NewInstanceFactory() {


    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ItemViewModel(itemListRepository) as T
    }



}