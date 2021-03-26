package fr.codingfactory.scanner.foodlist

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import fr.codingfactory.scanner.R
import fr.codingfactory.scanner.data.ItemDatabase
import fr.codingfactory.scanner.databinding.ActivityFoodBinding
import fr.codingfactory.scanner.requests.RetrofitClient

private const val TAG = "FoodListActivity"

class FoodListActivity : AppCompatActivity(), FoodAdapter.OnItemClickListener {

    private val model: FoodListViewModel by viewModels()

    private lateinit var binding: ActivityFoodBinding
    private lateinit var adapter: FoodAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFoodBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.i(TAG, "onCreate: ${model.getState().value}")

        model.itemDao = ItemDatabase.getDatabase(this).itemDao()
        model.service = RetrofitClient.itemService

        model.getState().observe(this, Observer { updateItems(it!!) })

        adapter = FoodAdapter(listOf(), this)

        binding.foodRecyclerView.adapter = adapter
        binding.foodRecyclerView.layoutManager = LinearLayoutManager(this)

        model.loadItems()

        findViewById<BottomNavigationView>(R.id.bottom_navigation).setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.ic_scan -> {
                    val launchSecondActivity = 1

                    val intent = Intent(this, FoodScanner::class.java)
                    startActivityForResult(intent, launchSecondActivity)
                }
                R.id.ic_home -> {
                    startActivity(Intent(this, FoodListActivity::class.java))
                }
            }
            true
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        Log.i(TAG, "onActivityResult: blablabla")

        if (resultCode == Activity.RESULT_OK) {
            model.getFood(data?.getStringExtra("barcode")!!)
        }

    }

    override fun onItemClick(position: Int) {
        adapter.notifyItemChanged(position)
        navigateToDetail(position)
    }

    private fun updateItems(state: FoodListViewModelState) {
        Log.i(TAG, "updateFoods: $state")

        when (state) {
            is FoodListViewModelState.Failure -> {
                //Toast.makeText(this, state.errorMessage, Toast.LENGTH_LONG).show()
            }

            FoodListViewModelState.Loading -> {

            }

            is FoodListViewModelState.LoadedItems -> {
                Log.i(TAG, "updateFoods: SUCCESS")
                adapter.updateDataSet(state.items)
            }
        }
    }

    private fun navigateToDetail(position: Int) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("item", adapter.getItems()[position])
        startActivity(intent)
    }
}