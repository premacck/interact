package com.techmesystem.intera.filter

import androidx.annotation.IdRes
import androidx.annotation.StringRes

/**
 * Prem's creation, on 2020-02-14
 */
interface FilterContainer {

  fun openFilterPage(@StringRes pageType: Int, selectedValue: String? = null)

  fun onFilterClick(type: Int, value: String? = null)

  fun onSortClick(@IdRes viewId: Int)
}