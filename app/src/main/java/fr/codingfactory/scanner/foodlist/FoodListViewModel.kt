package fr.codingfactory.scanner.foodlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import fr.codingfactory.scanner.R
import fr.codingfactory.scanner.models.Food

private val foods = listOf(
    Food("Banane", "24/03/2021","10:02", R.drawable.banane),
    Food("Kiwi", "24/03/2021","10:02", R.drawable.kiwi),
    Food("Banane", "24/03/2021","10:02", R.drawable.banane),
    Food("Kiwi", "24/03/2021","10:02", R.drawable.kiwi),
    Food("Banane", "24/03/2021","10:02", R.drawable.banane),
    Food("Kiwi", "24/03/2021","10:02", R.drawable.kiwi),
    Food("Banane", "24/03/2021","10:02", R.drawable.banane),
    Food("Kiwi", "24/03/2021","10:02", R.drawable.kiwi)
)


class FoodListViewModel : ViewModel() {

    private val foodsLiveData = MutableLiveData<List<Food>>()

    fun getFoodsLiveData(): LiveData<List<Food>> = foodsLiveData

    fun loadFoods(){
        foodsLiveData.value = foods
    }
}