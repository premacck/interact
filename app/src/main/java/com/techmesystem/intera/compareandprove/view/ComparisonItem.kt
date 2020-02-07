package com.techmesystem.intera.compareandprove.view

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import com.airbnb.epoxy.ModelProp
import com.airbnb.epoxy.ModelView
import com.techmesystem.intera.R
import com.techmesystem.intera.compareandprove.data.COMPARISON_ONOFRE
import com.techmesystem.intera.compareandprove.data.COMPARISON_SAO_PAULO
import com.techmesystem.intera.compareandprove.data.COMPARISON_ULTRA_FARMA
import com.techmesystem.intera.compareandprove.model.Comparison
import com.techmesystem.intera.util.attachLayout
import com.techmesystem.intera.util.onDebounceClick
import kotlinx.android.synthetic.main.item_compare.view.*
import org.jetbrains.anko.imageResource

/**
 * Prem's creation, on 2020-02-08
 */
@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT) class ComparisonItem @JvmOverloads constructor(
  context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

  init {
    attachLayout(R.layout.item_compare)
    cl_root.onDebounceClick { /* Do nothing as of now */ }
  }

  @ModelProp fun withComparison(comparison: Comparison) {
    iv_comparison_company_logo.imageResource = when (comparison.comparisonCompany) {
      COMPARISON_SAO_PAULO -> R.drawable.logo_sao_paulo
      COMPARISON_ULTRA_FARMA -> R.drawable.logo_ultra_farma
      COMPARISON_ONOFRE -> R.drawable.logo_onofre
      else -> R.drawable.logo_drogasil
    }
    tv_price.text = context.getString(R.string.dollar, comparison.price)
    tv_percentage.text = context.getString(R.string.x_percent, comparison.percentage)
  }
}