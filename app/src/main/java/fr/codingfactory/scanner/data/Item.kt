package fr.codingfactory.scanner.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "item_table")
data class Item (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val scanDate: String,
    val scanHour: String,
    val itemImageUrl: String
)