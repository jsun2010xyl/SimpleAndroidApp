package com.example.jsonkotlin1.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.jsonkotlin1.data.db.entity.Item

import androidx.room.Room

@Database(entities = [Item::class], version = 1)
abstract class Database1: RoomDatabase(){
    abstract fun itemDao() : ItemDao

    companion object {
        @Volatile private var instance: Database1? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext, Database1::class.java, "itemdb.db")
        .build()
    }
}