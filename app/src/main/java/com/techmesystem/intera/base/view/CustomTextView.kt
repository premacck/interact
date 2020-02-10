package com.techmesystem.intera.base.view

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.annotation.DrawableRes
import com.techmesystem.intera.R
import com.techmesystem.intera.util.attachLayout
import com.techmesystem.intera.util.onDebounceClick
import com.techmesystem.intera.util.setDrawables
import com.techmesystem.intera.util.showOrHide
import kotlinx.android.synthetic.main.custom_text_view.view.*
import org.jetbrains.anko.backgroundResource

/**
 * Prem's creation, on 2020-02-09
 */
class CustomTextView @JvmOverloads constructor(
  context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

  var text: String
    get() = tv_main_text?.text?.toString().orEmpty()
    set(value) {
      tv_main_text?.text = value
    }

  init {
    attachLayout(R.layout.custom_text_view)
    backgroundResource = R.drawable.ripple_rect

    val array = context.obtainStyledAttributes(attrs, R.styleable.CustomTextView)

    setTitle(array.getString(R.styleable.CustomTextView_ctv_title))
    mainText(array.getString(R.styleable.CustomTextView_ctv_mainText))
    boldTitle(array.getBoolean(R.styleable.CustomTextView_ctv_boldTitle, true))
    setDrawables(array.getResourceId(R.styleable.CustomTextView_ctv_drawableStart, 0))
    bottomLineVisible(array.getBoolean(R.styleable.CustomTextView_ctv_bottomLineVisible, true))

    array.recycle()
  }

  private fun setDrawables(@DrawableRes resourceId: Int) {
    tv_main_text?.setDrawables(start = resourceId, end = R.drawable.ic_next_small)
  }

  private fun boldTitle(isBold: Boolean) = tv_title.setTypeface(null, if (isBold) Typeface.BOLD else Typeface.NORMAL)

  private fun setTitle(string: String?) {
    tv_title?.showOrHide(!string.isNullOrEmpty()) {
      it.text = string
    }
  }

  private fun mainText(string: String?) {
    tv_main_text?.text = string
  }

  private fun bottomLineVisible(isVisible: Boolean) {
    v_bottom_line?.showOrHide(isVisible)
  }

  fun onClick(action: () -> Unit) = onDebounceClick(500, action)
}