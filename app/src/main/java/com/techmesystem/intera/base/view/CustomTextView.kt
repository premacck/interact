package com.techmesystem.intera.base.view

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.annotation.DrawableRes
import com.airbnb.epoxy.ModelProp
import com.airbnb.epoxy.ModelProp.Option.DoNotHash
import com.airbnb.epoxy.ModelView
import com.airbnb.epoxy.TextProp
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
@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT) class CustomTextView @JvmOverloads constructor(
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
    setDrawables(
      array.getResourceId(R.styleable.CustomTextView_ctv_drawableStart, 0), array.getResourceId(R.styleable.CustomTextView_ctv_drawableEnd, R.drawable.ic_next_small)
    )
    bottomLineVisible(array.getBoolean(R.styleable.CustomTextView_ctv_bottomLineVisible, true))

    array.recycle()
  }

  @ModelProp fun withHorizontalDrawables(startEnd: Pair<Int, Int>) = setDrawables(startEnd.first, startEnd.second)

  private fun setDrawables(@DrawableRes start: Int, @DrawableRes end: Int) {
    if (start == 0 && end == 0) {
      tv_main_text?.setDrawables()
      return
    }
    when {
      start == 0 -> tv_main_text?.setDrawables(end = end)
      end == 0 -> tv_main_text?.setDrawables(start = start)
      else -> tv_main_text?.setDrawables(start = start, end = end)
    }
  }

  private fun boldTitle(isBold: Boolean) = tv_title.setTypeface(null, if (isBold) Typeface.BOLD else Typeface.NORMAL)

  @JvmOverloads @TextProp fun setTitle(string: CharSequence? = null) {
    tv_title?.showOrHide(!string.isNullOrEmpty()) {
      it.text = string
    }
  }

  @TextProp fun mainText(string: CharSequence?) {
    text = string?.toString().orEmpty()
  }

  @JvmOverloads @ModelProp fun bottomLineVisible(isVisible: Boolean = false) {
    v_bottom_line?.showOrHide(isVisible)
  }

  @ModelProp(DoNotHash) fun onClick(action: (String) -> Unit) = onDebounceClick { action(text) }
}