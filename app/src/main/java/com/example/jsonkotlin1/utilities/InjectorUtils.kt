package com.example.jsonkotlin1.utilities

import com.example.jsonkotlin1.data.ItemRepository
import com.example.jsonkotlin1.viewmodels.ItemViewModelFactory

object InjectorUtils {
    fun provideItemViewModelFactory(): ItemViewModelFactory {
        val itemRepository = ItemRepository.getInstance()
        return ItemViewModelFactory(itemRepository)

    }
}