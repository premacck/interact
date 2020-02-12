package com.techmesystem.intera.profile.view

import android.content.Context
import android.util.AttributeSet
import androidx.cardview.widget.CardView
import com.airbnb.epoxy.ModelProp
import com.airbnb.epoxy.ModelProp.Option.DoNotHash
import com.airbnb.epoxy.ModelView
import com.airbnb.epoxy.TextProp
import com.techmesystem.intera.R
import com.techmesystem.intera.util.attachLayout
import com.techmesystem.intera.util.onDebounceClick
import kotlinx.android.synthetic.main.item_extract_interacash.view.*
import org.jetbrains.anko.dip

/**
 * Prem's creation, on 2020-02-12
 */
@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT) class ExtractInteracashItem @JvmOverloads constructor(
  context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : CardView(context, attrs, defStyleAttr) {

  init {
    attachLayout(R.layout.item_extract_interacash)
    radius = dip(6).toFloat()
    cardElevation = dip(8).toFloat()
  }

  @JvmOverloads @TextProp fun withMonth(text: CharSequence? = null) {
    tv_month?.text = text
  }

  @JvmOverloads @TextProp fun withYear(text: CharSequence? = null) {
    tv_year?.text = text
  }

  @ModelProp(DoNotHash) fun onMonthClick(action: () -> Unit) = tv_month?.onDebounceClick { action() }

  @ModelProp(DoNotHash) fun onYearClick(action: () -> Unit) = tv_year?.onDebounceClick { action() }
}