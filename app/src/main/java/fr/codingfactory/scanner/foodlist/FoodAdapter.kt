package fr.codingfactory.scanner.foodlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import fr.codingfactory.scanner.data.Item
import fr.codingfactory.scanner.databinding.ItemFoodBinding

class FoodAdapter(private var items: List<Item>): RecyclerView.Adapter<FoodAdapter.ViewHolder>() {
    class ViewHolder(val binding: ItemFoodBinding) : RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemFoodBinding.inflate(inflater, parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        with(holder.binding){
            titleTextView.text = item.title
            dateTextView.text = item.scanDate
            hourTextView.text = item.scanHour
            Picasso.get().load(item.itemImageUrl).into(foodImageView);
        }
    }

    override fun getItemCount(): Int = items.size

    fun updateDataSet(items: List<Item>) {
        this.items = items
        notifyDataSetChanged()
    }
}