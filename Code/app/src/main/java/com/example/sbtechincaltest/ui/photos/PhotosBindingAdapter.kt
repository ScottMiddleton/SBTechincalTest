package com.example.sbtechincaltest.ui.photos

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

@BindingAdapter("photoUrl")
fun ImageView.photoIV(photoUrl: String) {
    Picasso.get().load(photoUrl).into(this)
}

@BindingAdapter("title")
fun TextView.titleTV(title: String) {
    text = title
}