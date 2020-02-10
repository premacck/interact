package com.techmesystem.intera.checkout.ui

import android.content.Context
import android.os.Bundle
import com.techmesystem.intera.R
import com.techmesystem.intera.base.BaseActivity
import com.techmesystem.intera.payment.ui.FormOfPaymentActivity
import com.techmesystem.intera.request.model.ProductRequest
import com.techmesystem.intera.request.ui.RequestDetailActivity.Companion.REQUEST
import com.techmesystem.intera.util.onDebounceClick
import kotlinx.android.synthetic.main.activity_order_summary.*
import org.jetbrains.anko.startActivity

/**
 * Prem's creation, on 2020-02-09
 */
class OrderSummaryActivity : BaseActivity() {

  private lateinit var request: ProductRequest

  companion object {
    fun launch(from: Context?, request: ProductRequest) = from?.run {
      startActivity<OrderSummaryActivity>(REQUEST to request)
    }
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_order_summary)
    toolbar.onBackNavigation { onBackPressed() }
    request = intent?.getParcelableExtra(REQUEST) ?: return
    initView()
    initListeners()
  }

  private fun initView() {
    product_1.withProduct(request.product1)
    product_2.withProduct(request.product2)
  }

  private fun initListeners() {
    btn_checkout.onDebounceClick {
      OrderStatusActivity.launch(this)
    }
    order_logistics.onFormOfPaymentClick {
      FormOfPaymentActivity.launchForResult(this)
    }
  }
}