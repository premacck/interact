package com.techmesystem.intera.notification.model

import android.os.Parcelable
import androidx.annotation.StringRes
import com.techmesystem.intera.productdetail.model.Product
import kotlinx.android.parcel.Parcelize

/**
 * Prem's creation, on 2020-02-10
 */
@Parcelize data class InteraNotification(@StringRes val title: Int, val product: Product, val isRead: Boolean) : Parcelable {
  val icon: Int
    get() = product.icon
  val productCaption: String
    get() = product.shortOrFullCaption
  val productCompany: String
    get() = product.companyName
}