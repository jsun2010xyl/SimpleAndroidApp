package com.example.jsonkotlin1.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Items")
data class Item(
    @SerializedName("id")
    val id:Int,
    @SerializedName("listId")
    val listId:Int,
    @SerializedName("name")
    val name:String?){
    @PrimaryKey(autoGenerate = true)
    var uniqueId: Int = 0
}
