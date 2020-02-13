package com.techmesystem.intera.compareandprove.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.techmesystem.intera.R
import com.techmesystem.intera.base.BaseActivity
import com.techmesystem.intera.compareandprove.data.comparison1
import com.techmesystem.intera.compareandprove.data.comparison2
import com.techmesystem.intera.compareandprove.data.comparison3
import com.techmesystem.intera.compareandprove.data.comparison4
import com.techmesystem.intera.compareandprove.model.Comparison
import com.techmesystem.intera.compareandprove.view.comparisonItem
import com.techmesystem.intera.compareandprove.view.marketAverageHistoryItem
import com.techmesystem.intera.productdetail.model.Product
import com.techmesystem.intera.productdetail.ui.ProductAddedBottomSheet
import com.techmesystem.intera.util.buildModels
import com.techmesystem.intera.util.onDebounceClick
import com.techmesystem.intera.util.orZero
import kotlinx.android.synthetic.main.activity_compare_and_prove.*
import kotlinx.android.synthetic.main.item_add_product.*
import org.jetbrains.anko.selector
import org.jetbrains.anko.startActivityForResult

/**
 * Prem's creation, on 2020-02-07
 */
class CompareAndProveActivity : BaseActivity() {

  private lateinit var product: Product

  companion object {
    const val REQUEST_COMPARISON = 10
    const val PRODUCT = "product"
    fun launch(from: Activity?, product: Product) = from?.run {
      startActivityForResult<CompareAndProveActivity>(REQUEST_COMPARISON, PRODUCT to product)
    }
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_compare_and_prove)
    product = intent?.getParcelableExtra(PRODUCT) ?: return
    toolbar.onBackNavigation { onBackPressed() }
    initRecyclerView()
    initView()
    initListeners()
  }

  override fun onBackPressed() {
    setResult(Activity.RESULT_OK, Intent().putExtra(PRODUCT, product))
    finish()
  }

  private fun initRecyclerView() {
    val list = getMockComparisonList()
    erv_compare_and_prove.buildModels {
      list.forEachIndexed { index, comparison ->
        comparisonItem {
          id("$comparison at $index")
          withComparison(comparison)
        }
      }
      marketAverageHistoryItem {
        id("Average market history")
        withAveragePrice("26,71")
        onHistoryClick { PriceHistoryActivity.launch(this@CompareAndProveActivity) }
      }
    }
  }

  private fun initView() {
    item_product.showLeftMarker(false)
    item_product.showRightMarker(false)
    item_product.withProduct(product)
    tv_selection_count.text = product.selectionCount.toString()
    btn_favorite.isSelected = product.isFavorite
  }

  private fun initListeners() {
    cv_selection_count.onDebounceClick {
      val list = (1 .. 20).toList().map { it.toString() }
      selector("Select count", list) { dialog, position ->
        dialog.dismiss()
        val currentSelection = list[position]
        product.selectionCount = currentSelection.toIntOrNull().orZero()
        tv_selection_count.text = currentSelection
      }
    }
    btn_favorite.onDebounceClick {
      product.isFavorite = !product.isFavorite
      btn_favorite.isSelected = product.isFavorite
    }
    btn_add_product.onDebounceClick {
      ProductAddedBottomSheet().show(supportFragmentManager)
    }
  }

  private fun getMockComparisonList(): List<Comparison> = mutableListOf<Comparison>().apply {
    repeat(5) {
      add(comparison1)
      add(comparison2)
      add(comparison3)
      add(comparison4)
    }
  }
}