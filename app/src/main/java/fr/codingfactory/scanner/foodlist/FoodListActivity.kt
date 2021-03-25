package fr.codingfactory.scanner.foodlist

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import fr.codingfactory.scanner.data.Item
import fr.codingfactory.scanner.data.ItemViewModel
import fr.codingfactory.scanner.databinding.ActivityFoodBinding
import fr.codingfactory.scanner.requests.FoodService
import fr.codingfactory.scanner.requests.FoodWrapperApi
import fr.codingfactory.scanner.requests.mapFoodWrapperApiToFood
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class FoodListActivity : AppCompatActivity(), FoodAdapter.OnItemClickListener {

    private val model: FoodListViewModel by viewModels()
    private lateinit var binding: ActivityFoodBinding
    private lateinit var adapter: FoodAdapter

    private lateinit var mItemViewModel : ItemViewModel
    private lateinit var itemObject : Item

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFoodBinding.inflate(layoutInflater)
        setContentView(binding.root)



        //ToDo: Déplacer

        val url = "https://world.openfoodfacts.org/api/"

        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BASIC)
        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(url)
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

        val service = retrofit.create(FoodService::class.java)

        val testRequest = service.foodInformation("9002490205973")

        testRequest.enqueue(object : Callback<FoodWrapperApi> {
            @RequiresApi(Build.VERSION_CODES.O)
            override fun onResponse(
                call: Call<FoodWrapperApi>,
                response: Response<FoodWrapperApi>
            ) {
                val test = response.body()

                if (test != null) {

                    itemObject = mapFoodWrapperApiToFood(FoodWrapperApi(product = response.body()!!.product))
                    mItemViewModel = ViewModelProvider(this@FoodListActivity).get(ItemViewModel::class.java)
                    mItemViewModel.readAllData.observe(this@FoodListActivity, Observer { item ->
                        adapter.updateDataSet(item)
                    })

                    insertDataToDatabase()


                    Log.i("FoodListActivity", "onResponse: $itemObject")

                }
            }

            override fun onFailure(call: Call<FoodWrapperApi>, t: Throwable) {
                Log.i("TEST123", "onFailure: KO")
            }
        })


        model.getItemsLiveData().observe(this, Observer { foods -> updateFoods(foods!!) })

        adapter = FoodAdapter(listOf(), this)

        binding.foodRecyclerView.adapter = adapter
        binding.foodRecyclerView.layoutManager = LinearLayoutManager(this)

        model.loadItems()
    }

    override fun onItemClick(position: Int) {
        adapter.notifyItemChanged(position)
        navigateToDetail(position)
    }

    private fun insertDataToDatabase() {

        val item = Item(
            0,
            itemObject.title,
            itemObject.scanDate,
            itemObject.scanHour,
            itemObject.itemImageUrl,
            itemObject.nutrition_grades,
            itemObject.ingredients_text_fr
        )
        mItemViewModel.addItem(item)

        Toast.makeText(this, "YACINE T BOOOOOOOOOOOOOOOOO", Toast.LENGTH_LONG).show()

    }

    private fun updateFoods(items: List<Item>) {
        adapter.updateDataSet(items)
    }

    private fun navigateToDetail(position: Int){
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("item",adapter.getItems()[position])
        startActivity(intent)
    }
}