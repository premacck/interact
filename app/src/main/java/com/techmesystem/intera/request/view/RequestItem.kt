package com.techmesystem.intera.request.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import com.airbnb.epoxy.ModelProp
import com.airbnb.epoxy.ModelProp.Option.DoNotHash
import com.airbnb.epoxy.ModelView
import com.techmesystem.intera.R
import com.techmesystem.intera.productdetail.model.Product
import com.techmesystem.intera.request.model.ProductRequest
import com.techmesystem.intera.util.attachLayout
import com.techmesystem.intera.util.findDrawable
import com.techmesystem.intera.util.onDebounceClick
import com.techmesystem.intera.util.showOrHide
import kotlinx.android.synthetic.main.item_request.view.*
import kotlinx.android.synthetic.main.item_request.view.tv_total_amount
import kotlinx.android.synthetic.main.item_request_product.view.*
import org.jetbrains.anko.imageResource

/**
 * Prem's creation, on 2020-02-09
 */
@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT) class RequestItem @JvmOverloads constructor(
  context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

  private lateinit var request: ProductRequest

  init {
    attachLayout(R.layout.item_request)

    val array = context.obtainStyledAttributes(attrs, R.styleable.RequestItem)

    isBottomLayoutVisible(array.getBoolean(R.styleable.RequestItem_bottomLayoutVisible, true))

    array.recycle()
  }

  private fun isBottomLayoutVisible(isVisible: Boolean) {
    group_bottom_layout?.showOrHide(isVisible)
    cl_root?.background = if (isVisible) findDrawable(R.drawable.bg_rounded_corners) else null
  }

  @ModelProp fun withRequest(request: ProductRequest) {
    this.request = request
    tv_cashback_info?.showOrHide(!request.cashback.isNullOrEmpty()) {
      it.text = context.getString(R.string.you_received_x_back_on_this_purchase, request.cashback)
    }
    tv_request_number?.text = request.id
    tv_request_date?.text = request.date
    tv_total_amount?.text = context.getString(R.string.dollar, request.totalAmount)

    product_1?.setProduct(request.product1)
    product_2?.setProduct(request.product2)
  }

  private fun View.setProduct(product: Product) {
    iv_product_thumbnail?.imageResource = product.icon
    tv_caption?.text = product.caption
    tv_company?.text = product.companyName
    tv_total_amount?.text = context.getString(R.string.dollar, product.priceAssociated)
    tv_additional_interacash?.text = context.getString(R.string.additional_price_x, product.priceAdditional)
  }

  @ModelProp(DoNotHash) fun onDetailClick(action: (ProductRequest) -> Unit) = btn_details?.onDebounceClick { action(request) }

  @ModelProp(DoNotHash) fun onAskAgainClick(action: (ProductRequest) -> Unit) = btn_ask_again?.onDebounceClick { action(request) }

  @ModelProp(DoNotHash) fun onHelpClick(action: (ProductRequest) -> Unit) = btn_help?.onDebounceClick { action(request) }
}