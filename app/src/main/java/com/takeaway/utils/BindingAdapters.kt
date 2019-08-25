package com.takeaway.utils

import androidx.appcompat.widget.AppCompatRatingBar
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.takeaway.data.model.Restaurant
import com.takeaway.ui.restaurantlist.RestaurantListAdapter


object BindingAdapters {

    @BindingAdapter("rating")
    @JvmStatic
    fun setRating(ratingBar: AppCompatRatingBar?, rating: Double) {
        ratingBar?.rating = rating.toFloat()
    }
}


