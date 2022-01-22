package com.example.jsonkotlin1.utilities

import com.example.jsonkotlin1.models.FakeDatabase
import com.example.jsonkotlin1.models.ItemRepository
import com.example.jsonkotlin1.viewmodels.ItemViewModelFactory

object InjectorUtils {

    fun provideItemsViewModelFactory(): ItemViewModelFactory {
        val itemRepository = ItemRepository.getInstance(FakeDatabase.getInstance().itemDao)
        return ItemViewModelFactory(itemRepository)
    }
}