package fr.codingfactory.scanner.foodlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fr.codingfactory.scanner.databinding.ItemFoodBinding
import fr.codingfactory.scanner.models.Food

class FoodAdapter(private var foods: List<Food>): RecyclerView.Adapter<FoodAdapter.ViewHolder>() {
    class ViewHolder(val binding: ItemFoodBinding) : RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemFoodBinding.inflate(inflater, parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val food = foods[position]
        with(holder.binding){
            titleTextView.text = food.title
            dateTextView.text = food.scanDate
            hourTextView.text = food.scanHour
            foodImageView.setImageResource(food.foodImageId)
        }
    }

    override fun getItemCount(): Int = foods.size
    fun updateDataSet(foods: List<Food>) {
        this.foods = foods
        notifyDataSetChanged()
    }
}