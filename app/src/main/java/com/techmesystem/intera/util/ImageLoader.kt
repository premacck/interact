package com.techmesystem.intera.util

import android.widget.ImageView
import com.bumptech.glide.Glide

/**
 * Prem's creation, on 2020-02-05
 */
fun ImageView.loadUrl(url: String) {
  Glide.with(this).load(url).into(this)
}