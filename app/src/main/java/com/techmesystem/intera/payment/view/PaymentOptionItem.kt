package com.techmesystem.intera.payment.view

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import androidx.annotation.DrawableRes
import com.techmesystem.intera.R
import com.techmesystem.intera.util.attachLayout
import kotlinx.android.synthetic.main.item_payment_option.view.*
import org.jetbrains.anko.imageResource

/**
 * Prem's creation, on 2020-02-10
 */
class PaymentOptionItem @JvmOverloads constructor(
  context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

  init {
    orientation = HORIZONTAL
    attachLayout(R.layout.item_payment_option)

    val array = context.obtainStyledAttributes(attrs, R.styleable.PaymentOptionItem)

    withText(array.getString(R.styleable.PaymentOptionItem_poi_text))
    withDrawableStart(array.getResourceId(R.styleable.PaymentOptionItem_poi_drawableStart, 0))
    withDrawableEnd(array.getResourceId(R.styleable.PaymentOptionItem_poi_drawableEnd, R.drawable.ic_menu_grey))

    array.recycle()
  }

  private fun withDrawableStart(@DrawableRes resourceId: Int) {
    if (resourceId != 0) {
      iv_start_drawable?.imageResource = resourceId
    }
  }

  private fun withDrawableEnd(@DrawableRes resourceId: Int) {
    if (resourceId != 0) {
      iv_end_drawable?.imageResource = resourceId
    }
  }

  private fun withText(text: String?) {
    tv_payment_option.text = text
  }
}