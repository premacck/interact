package com.techmesystem.intera.base.view

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import com.techmesystem.intera.R
import com.techmesystem.intera.util.attachLayout
import com.techmesystem.intera.util.enableOrDisable
import kotlinx.android.synthetic.main.item_order_logistics.view.*

/**
 * Prem's creation, on 2020-02-09
 */
class OrderLogistics @JvmOverloads constructor(
  context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

  init {
    attachLayout(R.layout.item_order_logistics)

    val array = context.obtainStyledAttributes(attrs, R.styleable.OrderLogistics)

    isViewsEnabled(array.getBoolean(R.styleable.OrderLogistics_ol_viewsEnabled, true))

    array.recycle()
  }

  private fun isViewsEnabled(isEnabled: Boolean) {
    et_recipient?.enableOrDisable(isEnabled, 1f)
    et_address?.enableOrDisable(isEnabled, 1f)
    tv_payment?.enableOrDisable(isEnabled, 1f)
  }
}