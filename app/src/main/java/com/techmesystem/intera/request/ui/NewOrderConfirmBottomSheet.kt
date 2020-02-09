package com.techmesystem.intera.request.ui

import android.os.Bundle
import androidx.core.os.bundleOf
import com.techmesystem.intera.R
import com.techmesystem.intera.base.bottomsheet.InteraBottomSheet
import com.techmesystem.intera.checkout.ui.OrderSummaryActivity
import com.techmesystem.intera.request.model.ProductRequest
import com.techmesystem.intera.request.ui.RequestDetailActivity.Companion.REQUEST

/**
 * Prem's creation, on 2020-02-09
 */
class NewOrderConfirmBottomSheet : InteraBottomSheet() {

  private lateinit var request: ProductRequest

  companion object {
    fun newInstance(request: ProductRequest) = NewOrderConfirmBottomSheet().apply {
      arguments = bundleOf(REQUEST to request)
    }
  }

  override val TAG: String = NewOrderConfirmBottomSheet::class.java.name

  override val title: CharSequence?
    get() = getString(R.string.do_you_confirm_the_new_order)
  override val message: CharSequence?
    get() = null
  override val positiveButtonText: CharSequence?
    get() = getString(R.string.yes_i_confirm)
  override val positiveButtonAction: () -> Unit
    get() = {
      OrderSummaryActivity.launch(context, request)
      dismiss()
    }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    request = arguments?.getParcelable(REQUEST) ?: return
  }
}