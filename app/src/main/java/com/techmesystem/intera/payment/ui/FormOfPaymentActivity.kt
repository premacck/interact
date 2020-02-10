package com.techmesystem.intera.payment.ui

import android.app.Activity
import android.content.Context
import android.os.Bundle
import com.techmesystem.intera.R
import com.techmesystem.intera.base.BaseActivity
import com.techmesystem.intera.payment.view.CardDataActivity
import com.techmesystem.intera.util.onDebounceClick
import kotlinx.android.synthetic.main.activity_form_of_payment.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.startActivityForResult

/**
 * Prem's creation, on 2020-02-10
 */
class FormOfPaymentActivity : BaseActivity() {

  private var isForResult: Boolean = false

  companion object {
    private const val IS_FOR_RESULT = "isForResult"
    private const val REQUEST_FORM_OF_PAYMENT = 12
    fun launch(from: Context?) = from?.run { startActivity<FormOfPaymentActivity>() }

    fun launchForResult(from: Activity?) = from?.run { startActivityForResult<FormOfPaymentActivity>(REQUEST_FORM_OF_PAYMENT, IS_FOR_RESULT to true) }
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    isForResult = intent?.getBooleanExtra(IS_FOR_RESULT, false) ?: false
    setContentView(R.layout.activity_form_of_payment)
    toolbar.onBackNavigation { onBackPressed() }
    ctv_forms_of_payment.onDebounceClick {
      if (isForResult) {
        setResult(Activity.RESULT_OK)
        finish()
      } else {
        CardDataActivity.launch(this)
      }
    }
  }
}