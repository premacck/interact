package com.techmesystem.intera.filter.model

import android.os.Parcelable
import com.techmesystem.intera.filter.mockdata.BRAND_LIST
import com.techmesystem.intera.filter.mockdata.CATEGORY_LIST
import com.techmesystem.intera.filter.mockdata.TYPES_OF_MEDICINES_LIST
import kotlinx.android.parcel.Parcelize

/**
 * Prem's creation, on 2020-02-14
 */
@Parcelize data class FilterFacets(
  val categories: ArrayList<FilterValue> = CATEGORY_LIST,
  val brands: ArrayList<FilterValue> = BRAND_LIST,
  val typesOfMedicines: ArrayList<FilterValue> = TYPES_OF_MEDICINES_LIST,
  var sortId: Int = 0,
  val lowPrice: String = "00,00",
  val highPrice: String = "00,00"
) : Parcelable