package fr.codingfactory.scanner.foodlist

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import fr.codingfactory.scanner.R
import fr.codingfactory.scanner.data.Item
import fr.codingfactory.scanner.data.ItemDao
import fr.codingfactory.scanner.data.ItemDatabase
import fr.codingfactory.scanner.databinding.ActivityDetailBinding
import kotlin.properties.Delegates


class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private var score by Delegates.notNull<Int>()
    lateinit var itemDao: ItemDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val item = intent.getParcelableExtra<Item>("item")
        Log.i("YAYA", "onCreate:${item} ")
        when (item?.nutritionGrades) {
            "a" -> score = R.drawable.nutri_a
            "b" -> score = R.drawable.nutri_b
            "c" -> score = R.drawable.nutri_c
            "d" -> score = R.drawable.nutri_d
            "e" -> score = R.drawable.nutri_e
        }
        with(binding) {
            detailTitleTextView.text = item?.title
            ingreTitleTextView.text = "Ingrédients"
            detailDateTextView.text = item?.scanDate
            Picasso.get().load(item?.itemImageUrl).into(detailImageView)
            detailIngreTextView.text = item?.ingredientsTextFr
            nutriImageView.setImageResource(score)
        }

        itemDao = ItemDatabase.getDatabase(this).itemDao()

        binding.deleteButton.setOnClickListener {
            itemDao.deleteItem(item!!)
            navigateToFoodListActivity()
            finish()
        }


    }

    private fun navigateToFoodListActivity() {
        val intent = Intent(this, FoodListActivity::class.java)
        startActivity(intent)
    }
}