package com.techmesystem.intera.checkout.ui

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import com.techmesystem.intera.R
import com.techmesystem.intera.base.ContainerFragment
import com.techmesystem.intera.base.view.InteraToolbar
import com.techmesystem.intera.checkout.adapter.CheckoutController
import com.techmesystem.intera.checkout.mockdata.CART
import com.techmesystem.intera.checkout.mockdata.CART_EQUIVALENT
import com.techmesystem.intera.checkout.model.UserCart
import com.techmesystem.intera.productdetail.model.Product
import com.techmesystem.intera.util.orZero
import com.techmesystem.intera.util.showOrHide
import kotlinx.android.synthetic.main.fragment_checkout.*
import org.jetbrains.anko.backgroundColorResource
import org.jetbrains.anko.support.v4.selector
import org.jetbrains.anko.textResource

class CheckoutFragment : ContainerFragment(), CheckoutController.Listener {

  override val TAG: String = CheckoutFragment::class.java.name
  private lateinit var checkoutProducts: UserCart
  private lateinit var controller: CheckoutController
  override val toolbarLayout: InteraToolbar?
    get() = toolbar

  companion object {
    fun newInstance(backNavigationEnabled: Boolean = false) = CheckoutFragment().apply {
      arguments = bundleOf(BACK_NAVIGATION_ENABLED to backNavigationEnabled)
    }
  }

  override fun layoutRes(): Int = R.layout.fragment_checkout

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    val isEquivalent = System.currentTimeMillis() % 2 == 0L
    checkoutProducts = if (isEquivalent) CART_EQUIVALENT else CART
    initRecyclerView()
  }

  private fun initRecyclerView() {
    controller = CheckoutController(this)
    erv_product.itemAnimator?.changeDuration = 0
    erv_product.setController(controller)
    updateController()
  }

  private fun setDataAvailableLayout(isAvailable: Boolean) {
    group_calculate_shipping.showOrHide(isAvailable)
    group_content_layout.showOrHide(isAvailable) {
      cl_root.backgroundColorResource = R.color.white
      btn_proceed_with_order.textResource = R.string.proceed_with_order
      rg_payment_type.selectViewProgrammatically(R.id.rb_associated_total_text)
    }
    tv_no_content.showOrHide(!isAvailable) {
      cl_root.backgroundColorResource = R.color.color_f9f7eb
      btn_proceed_with_order.textResource = R.string.make_a_wish
    }
  }

  private fun updateController() {
    controller.setData(checkoutProducts.products)
    setDataAvailableLayout(checkoutProducts.products.isNotEmpty())
  }

  override fun onRemoveClick(product: Product) {
    checkoutProducts.products.remove(product)
    updateController()
  }

  override fun onFavoriteClick(product: Product) {
    product.isFavorite = !product.isFavorite
    updateController()
  }

  override fun onSelectionCountClick(product: Product) {
    val list = (1 .. 20).toList().map { it.toString() }
    selector("Select count", list) { dialog, position ->
      dialog.dismiss()
      val currentSelection = list[position]
      product.selectionCount = currentSelection.toIntOrNull().orZero()
      updateController()
    }
  }
}