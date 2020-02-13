package com.techmesystem.intera.search.model

import android.os.Parcelable
import androidx.annotation.DrawableRes
import com.techmesystem.intera.productdetail.model.Product
import kotlinx.android.parcel.Parcelize

/**
 * Prem's creation, on 2020-02-13
 */
@Parcelize data class ProductSearch(

  override val id: Int,

  val product: Product,

  override val text: String?,

  @DrawableRes override val icon: Int? = null

) : Parcelable, SearchItem