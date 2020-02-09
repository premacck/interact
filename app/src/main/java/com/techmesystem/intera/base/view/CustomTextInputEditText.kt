package com.techmesystem.intera.base.view

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import android.widget.FrameLayout
import com.techmesystem.intera.R
import com.techmesystem.intera.util.attachLayout
import kotlinx.android.synthetic.main.custom_text_input_edit_text.view.*
import org.jetbrains.anko.backgroundResource

/**
 * Prem's creation, on 2020-02-09
 */
class CustomTextInputEditText @JvmOverloads constructor(
  context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

  var text: String
    get() = et_main_text?.text?.toString().orEmpty()
    set(value) {
      et_main_text?.setText(value)
    }

  init {
    attachLayout(R.layout.custom_text_input_edit_text)
    backgroundResource = R.drawable.ripple_rect

    val array = context.obtainStyledAttributes(attrs, R.styleable.CustomTextInputEditText)

    setTitle(array.getString(R.styleable.CustomTextInputEditText_ctiet_title))
    mainText(array.getString(R.styleable.CustomTextInputEditText_ctiet_mainText))
    boldTitle(array.getBoolean(R.styleable.CustomTextInputEditText_ctiet_boldTitle, true))

    array.recycle()
  }

  private fun boldTitle(isBold: Boolean) = tv_title.setTypeface(null, if (isBold) Typeface.BOLD else Typeface.NORMAL)

  private fun setTitle(string: String?) {
    tv_title?.text = string
  }

  private fun mainText(string: String?) {
    et_main_text?.setText(string)
  }

  override fun setEnabled(enabled: Boolean) {
    et_main_text.isEnabled = enabled
    super.setEnabled(enabled)
  }

  override fun setClickable(clickable: Boolean) {
    et_main_text.isClickable = clickable
    super.setClickable(clickable)
  }
}