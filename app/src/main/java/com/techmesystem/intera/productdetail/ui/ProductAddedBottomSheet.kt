package com.techmesystem.intera.productdetail.ui

import com.techmesystem.intera.R
import com.techmesystem.intera.base.bottomsheet.InteraBottomSheet

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
    get() = { /*TODO: open checkout*/ }
  override val negativeButtonAction: () -> Unit
    get() = { /*TODO: open home and open bottom sheet*/ }
}