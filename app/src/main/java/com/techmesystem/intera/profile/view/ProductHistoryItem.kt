package com.techmesystem.intera.profile.view

import android.animation.AnimatorInflater
import android.content.Context
import android.util.AttributeSet
import androidx.cardview.widget.CardView
import com.airbnb.epoxy.ModelProp
import com.airbnb.epoxy.ModelProp.Option.DoNotHash
import com.airbnb.epoxy.ModelView
import com.techmesystem.intera.R
import com.techmesystem.intera.productdetail.model.Product
import com.techmesystem.intera.profile.model.ProductHistory
import com.techmesystem.intera.util.attachLayout
import com.techmesystem.intera.util.onDebounceClick
import com.techmesystem.intera.util.showOrHide
import kotlinx.android.synthetic.main.item_product_history.view.*
import org.jetbrains.anko.dip

/**
 * Prem's creation, on 2020-02-12
 */
@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT) class ProductHistoryItem @JvmOverloads constructor(
  context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : CardView(context, attrs, defStyleAttr) {

  private lateinit var history: ProductHistory

  init {
    attachLayout(R.layout.item_product_history)
    radius = dip(6).toFloat()
    cardElevation = dip(8).toFloat()
    stateListAnimator = AnimatorInflater.loadStateListAnimator(context, R.animator.scale_on_touch)
  }

  @ModelProp fun withHistory(history: ProductHistory) {
    divider_1?.showOrHide(!history.cashback.isNullOrEmpty())
    tv_savings?.showOrHide(!history.cashback.isNullOrEmpty()) {
      it.text = context.getString(R.string.you_saved_x_with_this_purchase, history.cashback)
    }
    tv_caption?.text = history.productName
    tv_company?.text = history.companyName
    tv_total_amount_paid?.text = context.getString(R.string.dollar, history.amountPaid)
  }

  @ModelProp(DoNotHash) fun onClick(action: (Product) -> Unit) = onDebounceClick { action(history.product) }
}