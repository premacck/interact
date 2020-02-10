package com.techmesystem.intera.payment.ui

import android.content.Context
import android.os.Bundle
import com.techmesystem.intera.R
import com.techmesystem.intera.base.BaseActivity
import com.techmesystem.intera.payment.view.CardDataActivity
import com.techmesystem.intera.util.disable
import com.techmesystem.intera.util.onDebounceClick
import kotlinx.android.synthetic.main.activity_form_of_payment_with_product.*
import org.jetbrains.anko.startActivity

/**
 * Prem's creation, on 2020-02-10
 */
class FormOfPaymentWithProductActivity : BaseActivity() {

  companion object {
    fun launch(from: Context?) = from?.run { startActivity<FormOfPaymentWithProductActivity>() }
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_form_of_payment_with_product)
    toolbar.onBackNavigation { onBackPressed() }
    poi_debit_or_credit_card.onDebounceClick {
      CardDataActivity.launch(this, toSave = true)
    }
    btn_continue.disable()
  }
}