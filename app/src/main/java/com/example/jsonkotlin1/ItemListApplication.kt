package com.example.jsonkotlin1

import android.app.Application
import com.example.jsonkotlin1.data.db.ItemListDatabase
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

class ItemListApplication: Application(), KodeinAware {
    override val kodein = Kodein.lazy{
        import (androidXModule(this@ItemListApplication))

        bind() from singleton { ItemListDatabase(instance()) }
    }
}