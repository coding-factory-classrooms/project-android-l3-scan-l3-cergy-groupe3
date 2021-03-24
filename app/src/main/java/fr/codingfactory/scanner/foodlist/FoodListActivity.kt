package fr.codingfactory.scanner.foodlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import fr.codingfactory.scanner.R
import fr.codingfactory.scanner.databinding.ActivityFoodBinding
import fr.codingfactory.scanner.models.Food

class FoodListActivity : AppCompatActivity() {

    private val model: FoodListViewModel by viewModels()
    private lateinit var binding: ActivityFoodBinding
    private lateinit var adapter: FoodAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFoodBinding.inflate(layoutInflater)
        setContentView(binding.root)

        model.getFoodsLiveData().observe(this, Observer{ foods -> updateFoods(foods!!)})

        adapter = FoodAdapter(listOf())

        binding.foodRecyclerView.adapter = adapter
        binding.foodRecyclerView.layoutManager = LinearLayoutManager(this)

        model.loadFoods()
    }

    private fun updateFoods(foods: List<Food>) {
        adapter.updateDataSet(foods)
    }
}