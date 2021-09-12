package com.example.userlistdemo.binder

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.userlistdemo.R

@BindingAdapter(value = ["loadImage"], requireAll = false)
fun loadImage(view: ImageView, imageUrl: String?) {
    imageUrl?.let {
        Glide.with(view)
            .load(it)
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)
            .fitCenter()
            .into(view)
    }
}