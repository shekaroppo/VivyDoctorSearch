package com.takeaway.utils

import androidx.appcompat.widget.AppCompatRatingBar
import androidx.databinding.BindingAdapter


object BindingAdapters {

    @BindingAdapter("rating")
    @JvmStatic
    fun setRating(ratingBar: AppCompatRatingBar?, rating: Double) {
        ratingBar?.rating = rating.toFloat()
    }
}


