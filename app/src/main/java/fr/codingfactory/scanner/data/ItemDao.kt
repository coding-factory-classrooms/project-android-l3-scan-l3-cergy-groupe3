package fr.codingfactory.scanner.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ItemDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addItem(item: Item)

    @Query("SELECT * FROM item_table ORDER BY id ASC")
    fun readAllData(): List<Item>

    @Delete
    fun deleteItem(item: Item)


}