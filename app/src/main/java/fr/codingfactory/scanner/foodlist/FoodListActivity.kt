package fr.codingfactory.scanner.foodlist

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import fr.codingfactory.scanner.databinding.ActivityFoodBinding
import fr.codingfactory.scanner.models.Food
import fr.codingfactory.scanner.models.FoodWrapperApi
import fr.codingfactory.scanner.models.mapFoodWrapperApiToFood
import fr.codingfactory.scanner.requests.FoodService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.logging.Level


class FoodListActivity : AppCompatActivity() {

    private val model: FoodListViewModel by viewModels()
    private lateinit var binding: ActivityFoodBinding
    private lateinit var adapter: FoodAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFoodBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //ToDo:

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

        val testRequest = service.foodInformation("5449000000996")

        testRequest.enqueue(object : Callback<FoodWrapperApi> {
            @RequiresApi(Build.VERSION_CODES.O)
            override fun onResponse(
                call: Call<FoodWrapperApi>,
                response: Response<FoodWrapperApi>
            ) {
                val test = response.body()

                if (test != null) {
                    val productName = response.body()!!.product.product_name
                    val imageUrl = response.body()!!.product.image_url

//                    Log.i("TEST123", "onResponse: $productName // $imageUrl")

                    val test11 = mapFoodWrapperApiToFood(FoodWrapperApi(product = response.body()!!.product))

                    Log.i("FoodListActivity", "onResponse: $test11")

                }
            }

            override fun onFailure(call: Call<FoodWrapperApi>, t: Throwable) {
                Log.i("TEST123", "onFailure: KO")
            }
        })


        model.getFoodsLiveData().observe(this, Observer { foods -> updateFoods(foods!!) })

        adapter = FoodAdapter(listOf())

        binding.foodRecyclerView.adapter = adapter
        binding.foodRecyclerView.layoutManager = LinearLayoutManager(this)

        model.loadFoods()
    }

    private fun updateFoods(foods: List<Food>) {
        adapter.updateDataSet(foods)
    }
}