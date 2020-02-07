package com.techmesystem.intera.compareandprove.view

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import com.airbnb.epoxy.ModelProp
import com.airbnb.epoxy.ModelProp.Option.DoNotHash
import com.airbnb.epoxy.ModelView
import com.techmesystem.intera.R
import com.techmesystem.intera.util.attachLayout
import com.techmesystem.intera.util.onDebounceClick
import kotlinx.android.synthetic.main.item_market_average_history.view.*

/**
 * prem's creation, on 2020-02-08
 */
@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT) class MarketAverageHistoryItem @JvmOverloads constructor(
  context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

  init {
    attachLayout(R.layout.item_market_average_history)
  }

  @ModelProp fun withAveragePrice(averagePrice: String) {
    tv_market_average_price.text = averagePrice
  }

  @ModelProp(DoNotHash) fun onHistoryClick(action: () -> Unit) = btn_average_market_price_history.onDebounceClick { action() }
}