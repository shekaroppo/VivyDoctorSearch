package com.takeaway.ui.restaurantlist

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.takeaway.data.model.Favourite
import com.takeaway.data.model.Restaurant
import com.takeaway.data.repository.TakeawayRepository
import com.takeaway.databinding.ItemRestaurantBinding

class RestaurantListAdapter(private val takeawayRepository: TakeawayRepository) : ListAdapter<Restaurant,RestaurantListAdapter.SearchViewHolder>(PlantDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val bindings: ItemRestaurantBinding = ItemRestaurantBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchViewHolder(bindings)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val restaurant = getItem(position)
        holder.bind(restaurant)
    }

    inner class SearchViewHolder(private val binding: ItemRestaurantBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(restaurant: Restaurant) {
            binding.restaurant = restaurant
            binding.status.text = restaurant.status.name
            binding.fav.setOnClickListener { view -> toggleFavButton((view as CheckBox).isChecked, restaurant) }
            binding.executePendingBindings()
        }

        private fun toggleFavButton(isChecked: Boolean, restaurant: Restaurant) {
            restaurant.favourite = isChecked
            if (isChecked)
                takeawayRepository.setFavorite(Favourite(restaurant.name))
            else
                takeawayRepository.removeFavourite(Favourite(restaurant.name))
        }
    }
}
private class PlantDiffCallback : DiffUtil.ItemCallback<Restaurant>() {

    override fun areItemsTheSame(oldItem: Restaurant, newItem: Restaurant): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: Restaurant, newItem: Restaurant): Boolean {
        return oldItem == newItem
    }
}