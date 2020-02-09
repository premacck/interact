package com.techmesystem.intera.checkout.ui

import android.os.Bundle
import android.view.View
import com.techmesystem.intera.R
import com.techmesystem.intera.base.bottomsheet.BaseBottomSheet
import com.techmesystem.intera.util.onDebounceClick
import kotlinx.android.synthetic.main.bottom_sheet_order_received.*

/**
 * Prem's creation, on 2020-02-09
 */
class OrderReceivedBottomSheet : BaseBottomSheet() {

  override val TAG: String = OrderReceivedBottomSheet::class.java.name

  override fun layoutRes(): Int = R.layout.bottom_sheet_order_received

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    btn_close?.onDebounceClick { dismiss() }
    btn_upload_and_compete?.onDebounceClick { dismiss() }
  }
}