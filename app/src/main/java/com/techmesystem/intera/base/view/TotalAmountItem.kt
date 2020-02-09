package com.techmesystem.intera.base.view

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import com.techmesystem.intera.R
import com.techmesystem.intera.util.attachLayout
import com.techmesystem.intera.util.findDrawable
import com.techmesystem.intera.util.round
import com.techmesystem.intera.util.showOrHide
import kotlinx.android.synthetic.main.item_total_amount.view.*

/**
 * Prem's creation, on 2020-02-09
 */
class TotalAmountItem @JvmOverloads constructor(
  context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

  private var purchaseAmount: String = "0"
  private var shippingCost: String = "0"
  private var interaCash: String = "0"

  init {
    attachLayout(R.layout.item_total_amount)

    val array = context.obtainStyledAttributes(attrs, R.styleable.TotalAmountItem)

    borderEnabled(array.getBoolean(R.styleable.TotalAmountItem_borderEnabled, true))
    withPurchaseAmount(array.getString(R.styleable.TotalAmountItem_amount_purchaseAmount))
    withShippingCost(array.getString(R.styleable.TotalAmountItem_amount_shippingCost))
    withInteraCash(array.getString(R.styleable.TotalAmountItem_amount_interaCash))
    cashbackAllowed(array.getBoolean(R.styleable.TotalAmountItem_amount_cashbackAllowed, false))

    array.recycle()
  }

  private fun cashbackAllowed(isAllowed: Boolean) {
    tv_cashback_info?.showOrHide(isAllowed)
  }

  private fun borderEnabled(enabled: Boolean) {
    cl_root?.background = if (enabled) findDrawable(R.drawable.bg_rounded_corners) else null
  }

  private fun withPurchaseAmount(purchaseAmount: String?) {
    purchaseAmount?.let { this.purchaseAmount = it }
    tv_total_purchase_amount.text = context.getString(R.string.dollar, this.purchaseAmount)
    updateTotalAmount()
  }

  private fun withShippingCost(shippingCost: String?) {
    shippingCost?.let { this.shippingCost = it }
    tv_shipping_cost.text = context.getString(R.string.dollar, this.shippingCost)
    updateTotalAmount()
  }

  private fun withInteraCash(interaCash: String?) {
    interaCash?.let { this.interaCash = it }
    tv_interacash.text = context.getString(R.string.dollar, this.interaCash)
    updateTotalAmount()
  }

  private fun updateTotalAmount() {
    try {
      val purchaseAmountFloat = purchaseAmount.replace(",", ".").toFloat()
      val shippingCostFloat = shippingCost.replace(",", ".").toFloat()
      val interaCashFloat = interaCash.replace(",", ".").toFloat()
      val totalAmountFloat = purchaseAmountFloat + shippingCostFloat + interaCashFloat
      tv_total_amount.text = context.getString(R.string.dollar, totalAmountFloat.round().toString().replace(".", ","))
    } catch (e: Exception) {
      e.printStackTrace()
    }
  }
}