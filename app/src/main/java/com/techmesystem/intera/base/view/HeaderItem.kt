package com.techmesystem.intera.base.view

import android.content.Context
import android.util.AttributeSet
import android.util.TypedValue
import android.widget.TextView
import com.airbnb.epoxy.ModelView
import com.airbnb.epoxy.TextProp
import com.techmesystem.intera.R
import org.jetbrains.anko.dip
import org.jetbrains.anko.horizontalPadding
import org.jetbrains.anko.textColorResource
import org.jetbrains.anko.verticalPadding

/**
 * Prem's creation, on 2020-02-13
 */
@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT) class HeaderItem @JvmOverloads constructor(
  context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : TextView(context, attrs, defStyleAttr) {

  init {
    verticalPadding = dip(8)
    horizontalPadding = dip(16)
    setTextSize(TypedValue.COMPLEX_UNIT_SP, 14f)
    textColorResource = R.color.color_B35C5B60
  }

  @TextProp fun withText(string: CharSequence?) {
    text = string
  }
}