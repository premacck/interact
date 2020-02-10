package com.techmesystem.intera.payment.ui

import android.content.Context
import android.os.Bundle
import com.techmesystem.intera.R
import com.techmesystem.intera.base.BaseActivity
import com.techmesystem.intera.payment.view.CardDataActivity
import com.techmesystem.intera.util.onDebounceClick
import kotlinx.android.synthetic.main.activity_form_of_payment.*
import org.jetbrains.anko.startActivity

/**
 * Prem's creation, on 2020-02-10
 */
class FormOfPaymentActivity : BaseActivity() {

  companion object {
    fun launch(from: Context?) = from?.run { startActivity<FormOfPaymentActivity>() }
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_form_of_payment)
    toolbar.onBackNavigation { onBackPressed() }
    ctv_forms_of_payment.onDebounceClick {
      CardDataActivity.launch(this)
    }
  }
}