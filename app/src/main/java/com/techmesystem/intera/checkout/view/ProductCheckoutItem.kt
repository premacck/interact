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
  private var isRemoveAllowed: Boolean = true
  private var isFavoriteAllowed: Boolean = true

  init {
    attachLayout(R.layout.item_product_checkout)

    val array = context.obtainStyledAttributes(attrs, R.styleable.ProductCheckoutItem)

    removeAllowed(array.getBoolean(R.styleable.ProductCheckoutItem_removeAllowed, true))
    favoriteAllowed(array.getBoolean(R.styleable.ProductCheckoutItem_favoriteAllowed, true))
    editAllowed(array.getBoolean(R.styleable.ProductCheckoutItem_editAllowed, true))
    cashbackAllowed(array.getBoolean(R.styleable.ProductCheckoutItem_cashbackAllowed, true))

    array.recycle()
  }

  private fun cashbackAllowed(allowed: Boolean) {
    tv_cashback_members_info?.showOrHide(allowed)
  }

  @ModelProp fun removeAllowed(allowed: Boolean) {
    isRemoveAllowed = allowed
    btn_remove?.showOrHide(allowed)
  }

  @ModelProp fun favoriteAllowed(allowed: Boolean) {
    isFavoriteAllowed = allowed
    btn_favorite?.showOrHide(allowed)
  }

  @ModelProp fun editAllowed(allowed: Boolean) {
    tv_selection_count?.showOrHide(allowed)
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
      it.text = context.getString(R.string.members_receive_x_on_this_purchase, cashback)
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

  @ModelProp fun isInFavoriteList(isInFavoriteList: Boolean) {
    btn_favorite?.imageResource = if (isInFavoriteList) R.drawable.ic_favorite_red else R.drawable.ic_favorite
  }

  private fun withImageThumbnail(@DrawableRes resourceId: Int) {
    if (resourceId != -1) {
      iv_product_thumbnail?.imageResource = resourceId
    }
  }

  private fun withFavorite(isFavorite: Boolean) {
    btn_favorite.isSelected = isFavorite
  }

  @JvmOverloads @ModelProp(DoNotHash) fun onRemoveClick(action: ((Product) -> Unit)? = null) = btn_remove?.onDebounceClick { action?.invoke(product) }

  @JvmOverloads @ModelProp(DoNotHash) fun onFavoriteClick(action: ((Product) -> Unit)? = null) = btn_favorite?.onDebounceClick { action?.invoke(product) }

  @JvmOverloads @ModelProp(DoNotHash) fun onSelectionCountClick(action: ((Product) -> Unit)? = null) = tv_selection_count?.onDebounceClick { action?.invoke(product) }
}