package com.techmesystem.intera.productdetail.view

import android.animation.AnimatorInflater
import android.content.Context
import android.util.AttributeSet
import android.util.Log
import androidx.annotation.DrawableRes
import androidx.cardview.widget.CardView
import com.airbnb.epoxy.ModelProp
import com.airbnb.epoxy.ModelView
import com.techmesystem.intera.R
import com.techmesystem.intera.productdetail.model.Product
import com.techmesystem.intera.util.attachLayout
import com.techmesystem.intera.util.showOrHide
import kotlinx.android.synthetic.main.item_product.view.*
import org.jetbrains.anko.imageResource

/**
 * Prem's creation, on 2020-02-05
 */
@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT) class ProductItem @JvmOverloads constructor(
  context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : CardView(context, attrs, defStyleAttr) {

  init {
    attachLayout(R.layout.item_product)
    stateListAnimator = AnimatorInflater.loadStateListAnimator(context, R.animator.elevate_on_touch)

    val array = context.obtainStyledAttributes(R.styleable.ProductItem)
    try {
      if (!isInEditMode) {
        withImageThumbnail(array.getResourceId(R.styleable.ProductItem_productThumbnailResource, -1))
        withProductCaption(array.getString(R.styleable.ProductItem_productCaption))
        withProductCompany(array.getString(R.styleable.ProductItem_productCompany))
        withPrice(array.getString(R.styleable.ProductItem_productPrice))
        withProductPriceAssociated(array.getString(R.styleable.ProductItem_productPriceAssociated))
        withProductPriceAdditionalAssociated(array.getString(R.styleable.ProductItem_productPriceAdditionalAssociated))
        showEdgeMarkers(array.getBoolean(R.styleable.ProductItem_showBars, true))
        isInList(false)
      }
    } catch (e: Exception) {
      Log.e("ProductItem", "ERROR", e)
    } finally {
      array.recycle()
    }
  }

  @ModelProp fun withProduct(product: Product) {
    withImageThumbnail(product.icon)
    withProductCaption(product.shortOrFullCaption)
    withProductCompany(product.companyName)
    withPrice(product.averagePrice)
    withProductPriceAssociated(product.priceAssociated)
    withProductPriceAdditionalAssociated(product.priceAdditional)
  }

  @ModelProp fun isInList(inList: Boolean) {
    marker_end?.showOrHide(!inList)
  }

  @ModelProp fun showEdgeMarkers(showBars: Boolean) {
    marker_end?.showOrHide(showBars)
    marker_start?.showOrHide(showBars)
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
}