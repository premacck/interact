package com.techmesystem.intera.search.model

import android.os.Parcelable
import androidx.annotation.DrawableRes
import kotlinx.android.parcel.Parcelize

/**
 * Prem's creation, on 2020-02-13
 */
@Parcelize data class PopularSearch(

  override val id: Int,

  override val text: String?,

  @DrawableRes override val icon: Int? = null

) : Parcelable, SearchItem