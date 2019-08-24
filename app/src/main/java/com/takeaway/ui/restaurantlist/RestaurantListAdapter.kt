package com.takeaway.ui.restaurantlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.takeaway.data.model.Restaurant
import com.takeaway.databinding.ItemRestaurantBinding

class RestaurantListAdapter(private var restaurant: ArrayList<Restaurant>,
                            private val eventHandler: RestaurantListEventHandler) : RecyclerView.Adapter<RestaurantListAdapter.SearchViewHolder>() {

    fun updateData(restaurant: List<Restaurant>) {
        this.restaurant.addAll(restaurant)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val bindings: ItemRestaurantBinding = ItemRestaurantBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchViewHolder(bindings)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val restaurant = restaurant[position]
        holder.bind(restaurant, eventHandler)
    }

    override fun getItemCount(): Int {
        return restaurant.size
    }

    fun clear() {
        restaurant.clear()
    }

    inner class SearchViewHolder(private val binding: ItemRestaurantBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(restaurant: Restaurant, eventHandler: RestaurantListEventHandler) {
            binding.restaurant = restaurant
            binding.status.text = restaurant.status.capitalize()
            binding.eventHandler = eventHandler
            binding.executePendingBindings()
        }
    }
}
