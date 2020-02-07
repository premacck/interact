package com.techmesystem.intera.productdetail.view

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import com.airbnb.epoxy.ModelView
import com.airbnb.epoxy.TextProp
import com.techmesystem.intera.R
import com.techmesystem.intera.util.attachLayout
import kotlinx.android.synthetic.main.item_tag.view.*

/**
 * Prem's creation, on 2020-02-05
 */
@ModelView(autoLayout = ModelView.Size.WRAP_WIDTH_WRAP_HEIGHT) class TagItem @JvmOverloads constructor(
  context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

  init {
    attachLayout(R.layout.item_tag)
  }

  @TextProp fun withTag(text: CharSequence?) {
    tv_tag?.text = text
  }
}