package com.techmesystem.intera.base.view

import android.content.Context
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.airbnb.epoxy.ModelProp
import com.airbnb.epoxy.ModelProp.Option.DoNotHash
import com.airbnb.epoxy.ModelView
import com.airbnb.epoxy.TextProp
import com.techmesystem.intera.R
import com.techmesystem.intera.util.findColor
import com.techmesystem.intera.util.onDebounceClick
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk27.coroutines.onClick

/**
 * Prem's creation, on 2020-02-13
 */
@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT) class SimpleTextLayout @JvmOverloads constructor(
  context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : CardView(context, attrs, defStyleAttr) {

  private var clickCount: Int = 0
  private var clickedTime: Long = 0
  private var layoutConfig: ((LinearLayout.LayoutParams, TextView) -> Unit)? = null
  private var containerConfig: ((LinearLayout) -> Unit)? = null

  companion object {
    private const val MAX_CLICK = 8
    private const val CLICK_INTERVAL = 500
  }

  /**
   * Main [TextView] with default attributes.
   * Defining [textView] like this with Anko Layouts automatically adds it to the enclosing class.
   */
  private lateinit var textView: TextView
  private val container = verticalLayout {
    textView = textView {
      textColor = findColor(R.color.color_5B5B5F)
      setTextSize(TypedValue.COMPLEX_UNIT_SP, 14F)
      setLineSpacing(dip(4).toFloat(), 1F)
      gravity = Gravity.CENTER_VERTICAL
      backgroundResource = R.drawable.ripple_rect
    }
  }

  override fun onAttachedToWindow() {
    super.onAttachedToWindow()
    (textView.layoutParams as? LinearLayout.LayoutParams)?.let { params ->
      layoutConfig?.invoke(params, textView)
      textView.layoutParams = params
    }
    containerConfig?.invoke(container)
  }

  @TextProp fun withText(text: CharSequence?) {
    textView.text = text
  }

  @JvmOverloads @ModelProp fun isCardVisible(isVisible: Boolean = true) {
    cardElevation = if (isVisible) 4f else 0f
    setCardBackgroundColor(findColor(if (isVisible) R.color.white else R.color.transparent))
  }

  @JvmOverloads @ModelProp(DoNotHash) fun withCardConfig(init: CardView.() -> Unit = {}) = this.init()

  @JvmOverloads @ModelProp(DoNotHash) fun withConfig(init: TextView.() -> Unit = {}) = textView.init()

  @JvmOverloads @ModelProp(DoNotHash) fun withLayoutConfig(init: ((LinearLayout.LayoutParams, TextView) -> Unit)? = null) {
    layoutConfig = init
  }

  @JvmOverloads @ModelProp(DoNotHash) fun withContainerConfig(init: LinearLayout.() -> Unit = {}) {
    containerConfig = init
  }

  @JvmOverloads @ModelProp(DoNotHash) fun onBurstClick(action: (() -> Unit)? = null) {
    if (action == null) return
    textView.onClick {
      clickCount++
      if (clickCount == MAX_CLICK) action()
      if ((clickedTime + CLICK_INTERVAL) < System.currentTimeMillis()) {
        clickCount = 1
      }
      clickedTime = System.currentTimeMillis()
    }
  }

  @JvmOverloads @ModelProp(DoNotHash) fun onClick(action: ((String) -> Unit)? = null) {
    if (action == null) return
    textView.onDebounceClick { action(textView.text?.toString().orEmpty()) }
  }
}