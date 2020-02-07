@file:Suppress("DEPRECATION")

package com.techmesystem.intera.productdetail.ui

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Html
import androidx.appcompat.view.menu.MenuBuilder
import androidx.core.view.postDelayed
import com.techmesystem.intera.R
import com.techmesystem.intera.base.BaseActivity
import com.techmesystem.intera.compareandprove.ui.CompareAndProveActivity
import com.techmesystem.intera.compareandprove.ui.CompareAndProveActivity.Companion.PRODUCT
import com.techmesystem.intera.compareandprove.ui.CompareAndProveActivity.Companion.REQUEST_COMPARISON
import com.techmesystem.intera.productdetail.data.PRODUCT_1
import com.techmesystem.intera.productdetail.data.PRODUCT_1_EQUIVALENT
import com.techmesystem.intera.productdetail.data.PRODUCT_2
import com.techmesystem.intera.productdetail.data.PRODUCT_2_EQUIVALENT
import com.techmesystem.intera.productdetail.model.Product
import com.techmesystem.intera.productdetail.view.tagItem
import com.techmesystem.intera.util.*
import douglasspgyn.com.github.circularcountdown.CircularCascadeCountdown
import douglasspgyn.com.github.circularcountdown.listener.CascadeListener
import kotlinx.android.synthetic.main.activity_product_detail.*
import kotlinx.android.synthetic.main.content_product_detail.*
import kotlinx.android.synthetic.main.drawer_product_detail.*
import kotlinx.android.synthetic.main.item_add_product.*
import org.jetbrains.anko.*

/**
 * Prem's creation, on 2020-02-05
 */
class ProductDetailActivity : BaseActivity() {

  private var isFirstProduct = true
  private var isEquivalent = false
  private lateinit var mainProduct: Product
  private val countdown: CircularCascadeCountdown by lazy { CircularCascadeCountdown(15665000, cc_seconds, cc_minutes, cc_hours) }

  companion object {
    private const val IS_FIRST_PRODUCT = "isFirstProduct"
    private const val IS_EQUIVALENT = "isEquivalent"

    fun launch(from: Context?, isFirstProduct: Boolean, isEquivalent: Boolean) =
      from?.run { startActivity<ProductDetailActivity>(IS_FIRST_PRODUCT to isFirstProduct, IS_EQUIVALENT to isEquivalent) }
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    initMembers()
    setContentView(R.layout.activity_product_detail)
    initView()
    initListeners()
  }

  private fun initMembers() {
    isFirstProduct = intent?.getBooleanExtra(IS_FIRST_PRODUCT, true) ?: true
    isEquivalent = intent?.getBooleanExtra(IS_EQUIVALENT, false) ?: false
    mainProduct = if (isFirstProduct) {
      if (isEquivalent) PRODUCT_1_EQUIVALENT else PRODUCT_1
    } else {
      if (isEquivalent) PRODUCT_2_EQUIVALENT else PRODUCT_2
    }
  }

  override fun onDestroy() {
    if (countdown.isRunning()) {
      countdown.stop()
    }
    super.onDestroy()
  }

  private fun initView() {
    toolbar.onBackNavigation { onBackPressed() }
    (toolbar.getMenu() as MenuBuilder).actionItems[1].setIcon(if (mainProduct.isFavorite) R.drawable.ic_favorite_filled else R.drawable.ic_favorite_empty)
    toolbar.onMenuClick {
      when (it.itemId) {
        R.id.action_share -> {
          toast(R.string.share_this_product)
          true
        }
        R.id.action_favorite -> {
          mainProduct.isFavorite = !mainProduct.isFavorite
          it.setIcon(if (mainProduct.isFavorite) R.drawable.ic_favorite_filled else R.drawable.ic_favorite_empty)
          true
        }
        else -> false
      }
    }
    btn_favorite.hideView()
    mainProduct.run {
      iv_image_thumbnails.imageResource = icon
      c_tags.buildModels {
        tags.forEachIndexed { index, tag ->
          tagItem { id("$tag for $index").withTag(tag) }
        }
      }
      tv_caption.text = caption
      tv_company.text = companyName
      tv_price.text = getString(R.string.average_price_x, averagePrice)

      group_in_stock.showOrHide(isInStock) {
        initInStockLayout(this)
      }
      group_out_of_stock.showOrInvisible(!isInStock) {
        initOutOfStockLayout()
      }
      tv_product_description.text = Html.fromHtml(getString(description))
      tv_selection_count.text = selectionCount.toString()
      group_package_leaflet.showOrHide(!isNextDrawAvailable)
      group_next_draw.showOrHide(isNextDrawAvailable) {
        initNextDrawLayout()
      }
    }
    pi_equivalent_drugs.withProduct(if (isFirstProduct) PRODUCT_1_EQUIVALENT else PRODUCT_2_EQUIVALENT)
    pi_equivalent_drugs.onDebounceClick { launch(this, isFirstProduct, true) }
    pi_also_check_it_out.withProduct(if (isFirstProduct) PRODUCT_2_EQUIVALENT else PRODUCT_1_EQUIVALENT)
    pi_also_check_it_out.onDebounceClick { launch(this, !isFirstProduct, true) }
  }

  private fun initInStockLayout(product: Product) = product.run {
    tv_not_associated_text.text = getString(R.string.dollar, priceNotAssociated)
    tv_associated_text.text = getString(R.string.dollar, priceAssociated)
    tv_additional_associated_text.text = getString(R.string.additional_price_x, priceAdditional)
    setCashbackLayout(this)
  }

  private fun initOutOfStockLayout() {
    tv_notify_me.onDebounceClick {
      val name = et_notify_name.text.toString()
      if (name.isNotEmpty()) {
        toast(getString(R.string.we_will_notify_you_on, name))
      }
    }
  }

  private fun setCashbackLayout(product: Product) = product.run {
    tv_cashback?.run {
      setDrawables(end = if (cashback.isNullOrEmpty()) 0 else R.drawable.ic_next)
      setViewPadding(start = dip(if (cashback.isNullOrEmpty()) 8 else 16), end = dip(if (cashback.isNullOrEmpty()) 8 else 2))
      allCaps = cashback.isNullOrEmpty()
      textColorResource = if (cashback.isNullOrEmpty()) R.color.white else R.color.color_333333
      backgroundResource = if (cashback.isNullOrEmpty()) R.drawable.bg_filled_orange_rounded_corner else R.drawable.bg_filled_yellow_rounded_corner
      text = if (cashback.isNullOrEmpty()) getString(R.string.best_price) else getString(R.string.cashback_x, cashback)
      onDebounceClick { if (!cashback.isNullOrEmpty()) openCashback() }
    }
    group_cashback_info.showOrHide(!cashback.isNullOrEmpty()) {
      tv_cashback_members_info.text = getString(R.string.get_cashback_detail_info_x, cashback)
      tv_cashback_members_info.onDebounceClick { openCashback() }
    }
  }

  private fun openCashback() {
    group_check_point.showView()
    tv_drawer_header.textResource = R.string.cash_back
    tv_product_description.textResource = R.string.topic_header
    if (dl_product_detail.isOpenEnd()) {
      dl_product_detail.closeEnd()
      dl_product_detail.postDelayed(400) {
        dl_product_detail.openEnd()
      }
    } else dl_product_detail.openEnd()
  }

  private fun initNextDrawLayout() {
    countdown.listener(object : CascadeListener {
      override fun onFinish() {
        group_package_leaflet.showView()
        group_next_draw.hideView()
      }
    }).start()
  }

  private fun initListeners() {
    tv_product_detail.onDebounceClick {
      group_check_point.hideView()
      tv_drawer_header.textResource = R.string.product_details
      tv_product_description.text = Html.fromHtml(getString(mainProduct.description))
      dl_product_detail.toggleEnd()
    }
    tv_package_leaflet.onDebounceClick {}
    btn_add_product.onDebounceClick {
      dl_product_detail.closeEnd()
    }
    cv_selection_count.onDebounceClick {
      val list = (1 .. 20).toList().map { it.toString() }
      selector("Select count", list) { dialog, position ->
        dialog.dismiss()
        val currentSelection = list[position]
        mainProduct.selectionCount = currentSelection.toIntOrNull().orZero()
        tv_selection_count.text = currentSelection
      }
    }
    btn_compare_and_prove.onDebounceClick {
      CompareAndProveActivity.launch(this, mainProduct)
    }
  }

  override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    super.onActivityResult(requestCode, resultCode, data)
    if (resultCode == Activity.RESULT_OK) {
      when (requestCode) {
        REQUEST_COMPARISON -> data?.getParcelableExtra<Product>(PRODUCT)?.let {
          mainProduct = it
          (toolbar.getMenu() as MenuBuilder).actionItems[1].setIcon(if (mainProduct.isFavorite) R.drawable.ic_favorite_filled else R.drawable.ic_favorite_empty)
        }
      }
    }
  }
}