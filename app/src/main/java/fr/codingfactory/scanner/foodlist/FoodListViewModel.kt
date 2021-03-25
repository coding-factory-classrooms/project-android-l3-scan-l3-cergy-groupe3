package fr.codingfactory.scanner.foodlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import fr.codingfactory.scanner.data.Item

private val items = emptyList<Item>()


class FoodListViewModel : ViewModel() {

    private val itemsLiveData = MutableLiveData<List<Item>>()

    fun getItemsLiveData(): LiveData<List<Item>> = itemsLiveData

    fun loadItems(){
        itemsLiveData.value = items
    }
}