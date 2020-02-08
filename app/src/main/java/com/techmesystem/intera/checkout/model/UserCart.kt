package com.techmesystem.intera.checkout.model

import android.os.Parcelable
import com.techmesystem.intera.productdetail.model.Product
import kotlinx.android.parcel.Parcelize

/**
 * Prem's creation, on 2020-02-08
 */
@Parcelize data class UserCart(var products: MutableList<Product>, val isShippingOptionAvailable: Boolean = true) : Parcelable