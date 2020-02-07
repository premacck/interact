package com.techmesystem.intera.productdetail.model

import android.os.Parcelable
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.techmesystem.intera.util.ifEmptyUse
import kotlinx.android.parcel.Parcelize

/**
 * Prem's creation, on 2020-02-05
 */
@Parcelize data class Product(
  @DrawableRes val icon: Int,
  val tags: List<String> = listOf(),
  val caption: String = "",
  val companyName: String = "",
  val averagePrice: String = "",
  val priceAssociated: String = "",
  val priceNotAssociated: String? = null,
  val priceAdditional: String = "",
  @StringRes val description: Int = 0,
  var selectionCount: Int = 0,
  val cashback: String? = null,
  val captionShort: String? = null,
  val isInStock: Boolean = true,
  val isNextDrawAvailable: Boolean = false,
  var isFavorite: Boolean = false
) : Parcelable {
  val shortOrFullCaption: String
    get() = captionShort ifEmptyUse caption
}