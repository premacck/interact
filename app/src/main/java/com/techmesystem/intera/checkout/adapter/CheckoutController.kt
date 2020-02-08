package com.techmesystem.intera.checkout.adapter

import com.airbnb.epoxy.TypedEpoxyController
import com.techmesystem.intera.checkout.view.productCheckoutItem
import com.techmesystem.intera.productdetail.model.Product

/**
 * Prem's creation, on 2020-02-08
 */
class CheckoutController(private val listener: Listener) : TypedEpoxyController<List<Product>>() {

  override fun buildModels(products: List<Product>?) {
    products?.forEachIndexed { index, product ->
      productCheckoutItem {
        id("$index $product")
        withProduct(product)
        onRemoveClick { listener.onRemoveClick(it) }
        onFavoriteClick { listener.onFavoriteClick(it) }
        onSelectionCountClick { listener.onSelectionCountClick(it) }
      }
    }
  }

  interface Listener {
    fun onRemoveClick(product: Product)
    fun onFavoriteClick(product: Product)
    fun onSelectionCountClick(product: Product)
  }
}