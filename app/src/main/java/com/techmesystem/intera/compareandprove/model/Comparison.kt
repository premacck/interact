package com.techmesystem.intera.compareandprove.model

import android.os.Parcelable
import com.techmesystem.intera.compareandprove.data.ComparisonCompany
import kotlinx.android.parcel.Parcelize

/**
 * Prem's creation, on 2020-02-08
 */
@Parcelize data class Comparison(@ComparisonCompany val comparisonCompany: Int, val price: String, val percentage: String) : Parcelable