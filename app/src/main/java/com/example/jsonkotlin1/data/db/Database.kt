package com.example.jsonkotlin1.data.db

import androidx.room.RoomDatabase
import com.example.jsonkotlin1.data.db.entity.Item
import androidx.room.Database

@Database(
    entities = [Item::class],
    version = 1
)
abstract class Database: RoomDatabase(){

}