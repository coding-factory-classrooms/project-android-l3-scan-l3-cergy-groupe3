package fr.codingfactory.scanner.foodlist

import android.os.Build
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import fr.codingfactory.scanner.data.Item
import fr.codingfactory.scanner.data.ItemDao
import fr.codingfactory.scanner.requests.ItemService
import fr.codingfactory.scanner.requests.ItemWrapperApi
import fr.codingfactory.scanner.requests.mapItemWrapperApiToItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private val items = emptyList<Item>()
private const val TAG = "FoodListViewModel"

sealed class FoodListViewModelState(
    open val errorMessage: String = ""
) {
    object Loading : FoodListViewModelState()
    data class Failure(override val errorMessage: String) : FoodListViewModelState(errorMessage)
    data class LoadedItems(val items: List<Item>) : FoodListViewModelState()
}

class FoodListViewModel : ViewModel() {

    private val state = MutableLiveData<FoodListViewModelState>()
    fun getState(): LiveData<FoodListViewModelState> = state

    lateinit var itemDao : ItemDao
    lateinit var service: ItemService

    private lateinit var itemObject: Item

    fun loadItems() {
        val items = itemDao.readAllData()
        state.value = FoodListViewModelState.LoadedItems(items)
    }

    fun getFood(barcode: String) {

        state.value = FoodListViewModelState.Loading

        val testRequest = service.itemInformation(barcode)

        testRequest.enqueue(object : Callback<ItemWrapperApi> {
            @RequiresApi(Build.VERSION_CODES.O)
            override fun onResponse(
                call: Call<ItemWrapperApi>,
                response: Response<ItemWrapperApi>
            ) {
                if (response.body() != null) {

                    val name = response.body()?.product?.product_name

                    if (!TextUtils.isEmpty(name)) {
                        itemObject =
                            mapItemWrapperApiToItem(ItemWrapperApi(product = response.body()!!.product))

                        insertDataToDatabase()

                        loadItems()

                        Log.i(TAG, "onResponse: $itemObject")
                    } else {
                        state.value = FoodListViewModelState.Failure("Erreur lors du scan")
                    }
                }
            }

            override fun onFailure(call: Call<ItemWrapperApi>, t: Throwable) {
                state.value = FoodListViewModelState
                    .Failure("Erreur lors du traitement de la requête")
            }
        })
    }

    private fun insertDataToDatabase() {

        val item = Item(
            0,
            itemObject.title,
            itemObject.scanDate,
            itemObject.scanHour,
            itemObject.itemImageUrl,
            itemObject.nutritionGrades,
            itemObject.ingredientsTextFr
        )

        itemDao.addItem(item)


    }

}