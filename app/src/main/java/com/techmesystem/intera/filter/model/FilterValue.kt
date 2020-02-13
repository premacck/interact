package com.techmesystem.intera.filter.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Prem's creation, on 2020-02-14
 */
@Parcelize data class FilterValue(val id: Int, val value: String, var isSelected: Boolean = false) : Parcelable