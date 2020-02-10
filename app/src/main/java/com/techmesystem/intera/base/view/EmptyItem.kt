package com.techmesystem.intera.base.view

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import com.airbnb.epoxy.ModelView
import com.airbnb.epoxy.TextProp
import com.techmesystem.intera.R
import com.techmesystem.intera.util.attachLayout
import kotlinx.android.synthetic.main.item_empty.view.*

/**
 * Prem's creation, on 2020-02-10
 */
@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_MATCH_HEIGHT) class EmptyItem @JvmOverloads constructor(
  context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

  init {
    attachLayout(R.layout.item_empty)
  }

  @TextProp fun withErrorMessage(message: CharSequence?) {
    tv_error_message?.text = message
  }
}