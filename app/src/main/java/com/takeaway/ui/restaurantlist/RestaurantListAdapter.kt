package com.takeaway.ui.restaurantlist

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.recyclerview.widget.RecyclerView
import com.takeaway.data.model.Favourite
import com.takeaway.data.model.Restaurant
import com.takeaway.data.repository.TakeawayRepository
import com.takeaway.databinding.ItemRestaurantBinding

class RestaurantListAdapter(private var restaurants: List<Restaurant>, private val takeawayRepository: TakeawayRepository) : RecyclerView.Adapter<RestaurantListAdapter.SearchViewHolder>() {

    fun updateData(restaurants: List<Restaurant>) {
        this.restaurants = restaurants
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val bindings: ItemRestaurantBinding = ItemRestaurantBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchViewHolder(bindings)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val restaurant = restaurants[position]
        holder.bind(restaurant)
    }

    override fun getItemCount(): Int {
        return restaurants.size
    }

    inner class SearchViewHolder(private val binding: ItemRestaurantBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(restaurant: Restaurant) {
            binding.restaurant = restaurant
            binding.status.text = restaurant.status.capitalize()
            binding.fav.setOnClickListener { view -> toggleFavButton((view as CheckBox).isChecked, restaurant) }
            binding.executePendingBindings()
        }

        private fun toggleFavButton(isChecked: Boolean, restaurant: Restaurant) {
            restaurant.favourite = isChecked
            if (isChecked)
                takeawayRepository.addToFavorite(Favourite(restaurant.name))
            else
                takeawayRepository.removeFromFavourite(Favourite(restaurant.name))
        }
    }
}
