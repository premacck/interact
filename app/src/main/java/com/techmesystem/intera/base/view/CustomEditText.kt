package com.techmesystem.intera.base.view

import android.content.Context
import android.text.InputType.*
import android.util.AttributeSet
import android.widget.FrameLayout
import com.techmesystem.intera.R
import com.techmesystem.intera.util.attachLayout
import com.techmesystem.intera.util.showOrHide
import kotlinx.android.synthetic.main.custom_edit_text.view.*

/**
 * Prem's creation, on 2020-02-09
 */
class CustomEditText @JvmOverloads constructor(
  context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

  var text: String
    get() = et_input_text_1?.text?.toString().orEmpty()
    set(value) {
      et_input_text_1?.setText(value)
    }
  var textTwo: String
    get() = et_input_text_2?.text?.toString().orEmpty()
    set(value) {
      et_input_text_2?.setText(value)
    }

  init {
    attachLayout(R.layout.custom_edit_text)

    val array = context.obtainStyledAttributes(attrs, R.styleable.CustomEditText)

    if (!isInEditMode) {
      setInputType(array.getInt(R.styleable.CustomEditText_cet_inputType, 0))
    }
    setTitle(array.getString(R.styleable.CustomEditText_cet_title))
    isDoubleInput(array.getBoolean(R.styleable.CustomEditText_cet_doubleFields, false))
    hintOne(array.getString(R.styleable.CustomEditText_cet_hintOne))
    hintTwo(array.getString(R.styleable.CustomEditText_cet_hintTwo))

    array.recycle()
  }

  private fun setInputType(type: Int) {
    when (type) {
      0 -> { // Normal
        et_input_text_1?.inputType = TYPE_CLASS_TEXT or TYPE_TEXT_FLAG_CAP_CHARACTERS
      }
      1 -> { // Number
        et_input_text_1?.inputType = TYPE_CLASS_NUMBER
      }
      2 -> { // Password
        et_input_text_1?.inputType = TYPE_CLASS_TEXT or TYPE_TEXT_VARIATION_PASSWORD
        tv_title?.typeface?.let { et_input_text_1?.typeface = it }
      }
      3 -> { // Email
        et_input_text_1?.inputType = TYPE_TEXT_VARIATION_EMAIL_ADDRESS
      }
    }
  }

  private fun setTitle(string: String?) {
    tv_title?.text = string
  }

  private fun hintOne(string: String?) {
    et_input_text_1?.hint = string
  }

  private fun hintTwo(string: String?) {
    et_input_text_2?.hint = string
  }

  private fun isDoubleInput(isDouble: Boolean) {
    et_input_text_2?.showOrHide(isDouble)
  }
}