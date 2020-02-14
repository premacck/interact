package com.techmesystem.intera.search.ui

import android.content.Context
import android.os.Bundle
import com.techmesystem.intera.R
import com.techmesystem.intera.base.BaseActivity
import com.techmesystem.intera.productdetail.data.PRODUCT_1
import com.techmesystem.intera.productdetail.ui.ProductDetailActivity
import com.techmesystem.intera.util.onDebounceClick
import kotlinx.android.synthetic.main.activity_enter_barcode.*
import org.jetbrains.anko.startActivity

/**
 * Prem's creation, on 2020-02-14
 */
class EnterBarcodeActivity : BaseActivity() {

  companion object {
    fun launch(from: Context?) = from?.run { startActivity<EnterBarcodeActivity>() }
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_enter_barcode)
    toolbar.onBackNavigation { onBackPressed() }
    initListeners()
  }

  private fun initListeners() {
    btn_search.onDebounceClick { ProductDetailActivity.launch(this, PRODUCT_1) }
  }
}