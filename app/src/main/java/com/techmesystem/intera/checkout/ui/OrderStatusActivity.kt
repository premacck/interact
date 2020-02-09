package com.techmesystem.intera.checkout.ui

import android.content.Context
import android.os.Bundle
import com.techmesystem.intera.R
import com.techmesystem.intera.base.BaseActivity
import org.jetbrains.anko.startActivity

/**
 * Prem's creation, on 2020-02-09
 */
class OrderStatusActivity : BaseActivity() {

  companion object {
    fun launch(from: Context?) = from?.run { startActivity<OrderStatusActivity>() }
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_order_status)
    OrderReceivedBottomSheet().show(supportFragmentManager)
  }
}