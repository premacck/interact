package com.techmesystem.intera.base.bottomsheet

import android.os.Bundle
import android.view.View
import androidx.annotation.DrawableRes
import com.techmesystem.intera.R
import com.techmesystem.intera.util.onDebounceClick
import com.techmesystem.intera.util.setDrawables
import kotlinx.android.synthetic.main.bottom_sheet_intera.*

/**
 * Prem's creation, on 2020-02-08
 */
abstract class InteraBottomSheet : BaseBottomSheet() {

  abstract val title: CharSequence?

  abstract val message: CharSequence?

  @DrawableRes open val messageStartDrawable: Int? = null

  abstract val positiveButtonText: CharSequence?

  abstract val positiveButtonAction: () -> Unit

  abstract val negativeButtonText: CharSequence?

  abstract val negativeButtonAction: () -> Unit

  override fun layoutRes(): Int = R.layout.bottom_sheet_intera

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    tv_title?.text = title
    tv_message?.text = message
    messageStartDrawable?.let { tv_message?.setDrawables(start = it) }

    btn_positive?.text = positiveButtonText
    btn_positive?.onDebounceClick { positiveButtonAction() }

    btn_negative?.text = negativeButtonText
    btn_negative?.onDebounceClick { negativeButtonAction() }
  }
}