package com.techmesystem.intera.productdetail.ui

import com.techmesystem.intera.R
import com.techmesystem.intera.base.bottomsheet.InteraBottomSheet
import com.techmesystem.intera.checkout.ui.CheckoutActivity
import com.techmesystem.intera.home.ui.HomeActivity

/**
 * Prem's creation, on 2020-02-08
 */
class ProductAddedBottomSheet : InteraBottomSheet() {

  override val TAG: String = ProductAddedBottomSheet::class.java.name

  override val title: CharSequence?
    get() = context?.getString(R.string.product_added)
  override val message: CharSequence?
    get() = context?.getString(R.string.you_saved_x, "10,00")
  override val messageStartDrawable: Int?
    get() = R.drawable.ic_you_saved
  override val positiveButtonText: CharSequence?
    get() = context?.getString(R.string.checkout)
  override val negativeButtonText: CharSequence?
    get() = context?.getString(R.string.keep_buying)
  override val positiveButtonAction: () -> Unit
    get() = { CheckoutActivity.launch(context) }
  override val negativeButtonAction: () -> Unit
    get() = { HomeActivity.launchFresh(context) }
}