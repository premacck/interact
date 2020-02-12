package com.techmesystem.intera.profile.ui

import android.content.Context
import android.os.Bundle
import com.techmesystem.intera.R
import com.techmesystem.intera.base.BaseActivity
import com.techmesystem.intera.payment.mockdata.MonthList
import com.techmesystem.intera.payment.mockdata.YearList
import com.techmesystem.intera.payment.ui.FormOfPaymentWithProductActivity
import com.techmesystem.intera.productdetail.model.Product
import com.techmesystem.intera.productdetail.ui.ProductDetailActivity
import com.techmesystem.intera.profile.adapter.ProductHistoryController
import com.techmesystem.intera.profile.mockdata.HISTORY_LIST
import com.techmesystem.intera.util.afterModelBuild
import com.techmesystem.intera.util.onDebounceClick
import kotlinx.android.synthetic.main.activity_product_history.*
import org.jetbrains.anko.selector
import org.jetbrains.anko.startActivity

/**
 * Prem's creation, on 2020-02-12
 */
class ProductHistoryActivity : BaseActivity(), ProductHistoryController.Listener {

  private val months: MonthList by lazy { MonthList() }
  private val years: YearList by lazy { YearList() }
  private lateinit var controller: ProductHistoryController
  private val historyList = HISTORY_LIST

  companion object {
    fun launch(from: Context?) = from?.run { startActivity<ProductHistoryActivity>() }
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_product_history)
    toolbar.onBackNavigation { onBackPressed() }
    initView()
    initRecyclerView()
    initListeners()
  }

  private fun initView() {
    tv_interacash_balance?.text = getString(R.string.dollar, "10,00")
  }

  private fun initRecyclerView() {
    controller = ProductHistoryController(this)
    erv_history?.setController(controller)
    updateController()
  }

  private fun updateController() = controller.setData(historyList)

  private fun initListeners() {
    btn_add?.onDebounceClick { AddMoneyActivity.launch(this) }
    tv_interacash_balance?.onDebounceClick { FormOfPaymentWithProductActivity.launch(this) }
    tv_edit_interacash_balance?.onDebounceClick { FormOfPaymentWithProductActivity.launch(this) }
  }

  override fun onProductClick(product: Product) {
    ProductDetailActivity.launch(this, product)
  }

  override fun selectAndSetMonth() {
    selector(getString(R.string.select_month), months) { dialog, index ->
      controller.selectedMonth = months[index]
      updateController()
      controller.afterModelBuild { erv_history?.scrollToPosition(0) }
      dialog.dismiss()
    }
  }

  override fun selectAndSetYear() {
    selector(getString(R.string.select_year), years) { dialog, index ->
      controller.selectedYear = years[index]
      updateController()
      controller.afterModelBuild { erv_history?.scrollToPosition(0) }
      dialog.dismiss()
    }
  }
}