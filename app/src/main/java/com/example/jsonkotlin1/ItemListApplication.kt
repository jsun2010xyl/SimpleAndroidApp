package com.example.jsonkotlin1

import android.app.Application
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware

class ItemListApplication: Application(), KodeinAware {
    override val kodein = Kodein.lazy{

    }
}