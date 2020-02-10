package com.techmesystem.intera.base.bottomsheet

import android.os.Bundle
import android.view.View
import androidx.annotation.DrawableRes
import com.techmesystem.intera.R
import com.techmesystem.intera.util.onDebounceClick
import com.techmesystem.intera.util.setDrawables
import com.techmesystem.intera.util.showOrHide
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

  open val negativeButtonText: CharSequence? = null

  open val negativeButtonAction: () -> Unit = {}

  override fun layoutRes(): Int = R.layout.bottom_sheet_intera

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    tv_title?.showOrHide(!title.isNullOrEmpty()) {
      it.text = title
    }
    tv_message?.showOrHide(!message.isNullOrEmpty()) {
      it.text = message
      messageStartDrawable?.let { drawable -> it.setDrawables(start = drawable) }
    }

    btn_positive?.showOrHide(!positiveButtonText.isNullOrEmpty()) {
      it.text = positiveButtonText
      it.onDebounceClick { positiveButtonAction() }
    }
    btn_negative?.showOrHide(!negativeButtonText.isNullOrEmpty()) {
      it.text = negativeButtonText
      it.onDebounceClick { negativeButtonAction() }
    }
  }
}