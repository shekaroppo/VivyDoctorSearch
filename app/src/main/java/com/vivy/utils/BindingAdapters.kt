package com.vivy.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.bumptech.glide.request.RequestOptions
import com.vivy.R


object BindingAdapters {

    @BindingAdapter(value = ["bind:doctorId", "bind:name"])
    @JvmStatic
    fun bindImageUrl(view: ImageView, doctorId: String?, name: String) {
        val context = view.context
        Glide.with(context)
                .load(getUrlWithHeaders("${Constants.BASE_URL}/api/doctors/$doctorId/keys/profilepictures?name=$name"))
                .apply(RequestOptions().circleCrop().override(context.resources.getDimensionPixelSize(R.dimen.avatar_size)).placeholder(R.drawable.ic_account))
                .into(view)
    }

    private fun getUrlWithHeaders(url: String): GlideUrl {
        return GlideUrl(url, LazyHeaders.Builder()
                .addHeader("Authorization", "Bearer " + Constants.ACCESS_TOKEN)
                .addHeader("Content-type", "application/x-www-form-urlencoded")
                .build())
    }
}


