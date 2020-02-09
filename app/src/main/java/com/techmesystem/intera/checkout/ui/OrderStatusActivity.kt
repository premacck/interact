package com.techmesystem.intera.checkout.ui

import android.content.Context
import android.os.Bundle
import com.techmesystem.intera.R
import com.techmesystem.intera.base.BaseActivity
import kotlinx.android.synthetic.main.activity_order_status.*
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
    toolbar.onBackNavigation { onBackPressed() }
    OrderReceivedBottomSheet().show(supportFragmentManager)
  }
}