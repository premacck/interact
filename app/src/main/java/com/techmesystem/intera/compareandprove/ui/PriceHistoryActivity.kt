package com.techmesystem.intera.compareandprove.ui

import android.app.Activity
import android.os.Bundle
import com.techmesystem.intera.R
import com.techmesystem.intera.base.BaseActivity
import kotlinx.android.synthetic.main.activity_price_history.*
import org.jetbrains.anko.startActivity

/**
 * Prem's creation, on 2020-02-08
 */
class PriceHistoryActivity : BaseActivity() {

  companion object {
    fun launch(from: Activity?) = from?.run { startActivity<PriceHistoryActivity>() }
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_price_history)
    toolbar.onBackNavigation { onBackPressed() }
  }
}