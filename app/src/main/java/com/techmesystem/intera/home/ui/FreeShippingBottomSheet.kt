package com.techmesystem.intera.home.ui

import android.os.Bundle
import android.view.View
import com.techmesystem.intera.R
import com.techmesystem.intera.base.bottomsheet.BaseBottomSheet
import com.techmesystem.intera.home.adapter.CasmaticProductAdapter
import com.techmesystem.intera.util.onDebounceClick
import kotlinx.android.synthetic.main.bottom_sheet_free_shipping.*

/**
 * Prem's creation, on 2020-02-12
 */
class FreeShippingBottomSheet : BaseBottomSheet() {

  override val TAG: String = FreeShippingBottomSheet::class.java.name

  override fun layoutRes(): Int = R.layout.bottom_sheet_free_shipping

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    rv_suggestions?.adapter = context?.let { CasmaticProductAdapter(it, ArrayList()) }
    btn_do_not_want_free_shipping?.onDebounceClick {
      dismiss()
    }
  }
}