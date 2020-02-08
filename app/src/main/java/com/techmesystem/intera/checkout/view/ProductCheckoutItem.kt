package com.techmesystem.intera.checkout.view

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.annotation.DrawableRes
import com.airbnb.epoxy.ModelProp
import com.airbnb.epoxy.ModelProp.Option.DoNotHash
import com.airbnb.epoxy.ModelView
import com.techmesystem.intera.R
import com.techmesystem.intera.productdetail.model.Product
import com.techmesystem.intera.util.attachLayout
import com.techmesystem.intera.util.onDebounceClick
import com.techmesystem.intera.util.showOrHide
import kotlinx.android.synthetic.main.item_product_checkout.view.*
import org.jetbrains.anko.imageResource

/**
 * Prem's creation, on 2020-02-08
 */
@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT) class ProductCheckoutItem @JvmOverloads constructor(
  context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

  private lateinit var product: Product

  init {
    attachLayout(R.layout.item_product_checkout)
  }

  @ModelProp fun withProduct(product: Product) {
    this.product = product
    withImageThumbnail(product.icon)
    withProductCaption(product.shortOrFullCaption)
    withProductCompany(product.companyName)
    withPrice(product.averagePrice)
    withProductPriceAssociated(product.priceAssociated)
    withProductPriceAdditionalAssociated(product.priceAdditional)
    withMembersCashback(product.cashback)
    withFavorite(product.isFavorite)
    withSelectionCount(product.selectionCount)
  }

  private fun withSelectionCount(selectionCount: Int) {
    tv_selection_count.text = selectionCount.toString()
  }

  private fun withMembersCashback(cashback: String?) {
    tv_cashback_members_info?.showOrHide(!cashback.isNullOrEmpty()) {
      it.text = cashback
    }
  }

  private fun withProductCaption(string: String?) {
    tv_caption?.showOrHide(!string.isNullOrEmpty()) {
      it.text = string
    }
  }

  private fun withProductCompany(string: String?) {
    tv_company?.showOrHide(!string.isNullOrEmpty()) {
      it.text = string
    }
  }

  private fun withPrice(string: String?) {
    tv_price_text?.showOrHide(!string.isNullOrEmpty()) {
      it.text = context.getString(R.string.average_price_x, string)
    }
  }

  private fun withProductPriceAssociated(string: String?) {
    tv_associated_text?.showOrHide(!string.isNullOrEmpty()) {
      it.text = context.getString(R.string.associated_price_x, string)
    }
  }

  private fun withProductPriceAdditionalAssociated(string: String?) {
    tv_additional_associated_text?.showOrHide(!string.isNullOrEmpty()) {
      it.text = context.getString(R.string.additional_price_x, string)
    }
  }

  private fun withImageThumbnail(@DrawableRes resourceId: Int) {
    if (resourceId != -1) {
      iv_product_thumbnail?.imageResource = resourceId
    }
  }

  private fun withFavorite(isFavorite: Boolean) {
    btn_favorite.isSelected = isFavorite
  }

  @ModelProp(DoNotHash) fun onRemoveClick(action: (Product) -> Unit) = btn_remove?.onDebounceClick { action(product) }

  @ModelProp(DoNotHash) fun onFavoriteClick(action: (Product) -> Unit) = btn_favorite?.onDebounceClick { action(product) }

  @ModelProp(DoNotHash) fun onSelectionCountClick(action: (Product) -> Unit) = tv_selection_count?.onDebounceClick { action(product) }
}