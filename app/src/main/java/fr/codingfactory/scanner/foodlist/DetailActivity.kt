package fr.codingfactory.scanner.foodlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.squareup.picasso.Picasso
import fr.codingfactory.scanner.R
import fr.codingfactory.scanner.data.Item
import fr.codingfactory.scanner.databinding.ActivityDetailBinding
import kotlin.properties.Delegates


class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private var score by Delegates.notNull<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val item = intent.getParcelableExtra<Item>("item")
        Log.i("YAYA", "onCreate:${item} ")
        when(item?.nutrition_grades){
            "a" -> score = R.drawable.nutri_a
            "b" -> score = R.drawable.nutri_b
            "c" -> score = R.drawable.nutri_c
            "d" -> score = R.drawable.nutri_d
            "e" -> score = R.drawable.nutri_e
        }
        with(binding){
            detailTitleTextView.text = item?.title
            ingreTitleTextView.text = "Ingrédients"
            detailDateTextView.text = item?.scanDate
            Picasso.get().load(item?.itemImageUrl).into(detailImageView)
            detailIngreTextView.text = item?.ingredients_text_fr
            nutriImageView.setImageResource(score)
        }
    }
}