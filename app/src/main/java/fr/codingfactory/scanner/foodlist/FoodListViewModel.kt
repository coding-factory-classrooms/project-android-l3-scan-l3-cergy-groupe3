package fr.codingfactory.scanner.foodlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import fr.codingfactory.scanner.R
import fr.codingfactory.scanner.models.Food

private val foods = listOf(
    Food("Banane", "24/03/2021","10:02", "https://assets.stickpng.com/images/580b57fcd9996e24bc43c137.png"),
    Food("Kiwi", "24/03/2021","10:02", "https://image-uviadeo.journaldunet.com/image/450/1892845908/421969.jpg"),
    Food("Banane", "24/03/2021","10:02", "https://assets.stickpng.com/images/580b57fcd9996e24bc43c137.png"),
    Food("Kiwi", "24/03/2021","10:02", "https://image-uviadeo.journaldunet.com/image/450/1892845908/421969.jpg"),
    Food("Banane", "24/03/2021","10:02", "https://assets.stickpng.com/images/580b57fcd9996e24bc43c137.png"),
    Food("Kiwi", "24/03/2021","10:02", "https://image-uviadeo.journaldunet.com/image/450/1892845908/421969.jpg"),
    Food("Banane", "24/03/2021","10:02", "https://assets.stickpng.com/images/580b57fcd9996e24bc43c137.png"),
    Food("Kiwi", "24/03/2021","10:02", "https://image-uviadeo.journaldunet.com/image/450/1892845908/421969.jpg"),
    Food("Banane", "24/03/2021","10:02", "https://assets.stickpng.com/images/580b57fcd9996e24bc43c137.png"),
    Food("Kiwi", "24/03/2021","10:02", "https://image-uviadeo.journaldunet.com/image/450/1892845908/421969.jpg"),
    Food("Banane", "24/03/2021","10:02", "https://assets.stickpng.com/images/580b57fcd9996e24bc43c137.png"),
    Food("Kiwi", "24/03/2021","10:02", "https://image-uviadeo.journaldunet.com/image/450/1892845908/421969.jpg")
)


class FoodListViewModel : ViewModel() {

    private val foodsLiveData = MutableLiveData<List<Food>>()

    fun getFoodsLiveData(): LiveData<List<Food>> = foodsLiveData

    fun loadFoods(){
        foodsLiveData.value = foods
    }
}