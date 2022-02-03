package com.example.jsonkotlin1

import android.app.Application
import com.example.jsonkotlin1.data.ConnectivityInterceptor
import com.example.jsonkotlin1.data.ConnectivityInterceptorImpl
import com.example.jsonkotlin1.data.ItemApiService
import com.example.jsonkotlin1.data.db.ItemListDatabase
import com.example.jsonkotlin1.data.network.NetworkDataSource
import com.example.jsonkotlin1.data.network.NetworkDataSourceImpl
import com.example.jsonkotlin1.data.repository.ItemListRepository
import com.example.jsonkotlin1.data.repository.ItemListRepositoryImpl
import com.example.jsonkotlin1.viewmodels.ItemViewModelFactory
import com.jakewharton.threetenabp.AndroidThreeTen
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class ItemListApplication: Application(), KodeinAware {
    override val kodein = Kodein.lazy{
        import (androidXModule(this@ItemListApplication))

        bind() from singleton { ItemListDatabase(instance()) }
        bind() from singleton { instance<ItemListDatabase>().itemDao() }
        bind<ConnectivityInterceptor>() with singleton { ConnectivityInterceptorImpl(instance()) }
        bind() from singleton { ItemApiService(instance()) }
        bind<NetworkDataSource>() with singleton { NetworkDataSourceImpl(instance()) }
        bind<ItemListRepository>() with singleton { ItemListRepositoryImpl(instance(), instance()) }
        bind() from provider { ItemViewModelFactory(instance()) }
    }

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
    }
}