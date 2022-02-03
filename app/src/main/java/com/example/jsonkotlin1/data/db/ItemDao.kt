package com.example.jsonkotlin1.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.jsonkotlin1.data.db.entity.Item

@Dao
interface ItemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(itemEntry: List<Item>)

    // TODO : 这里是select语句
    @Query("select * from Items where name is not null and name <> '' group by listId order by name ASC")
    fun getItems(): LiveData<List<Item>>
}