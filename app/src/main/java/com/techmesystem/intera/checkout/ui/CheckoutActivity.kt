package com.techmesystem.intera.checkout.ui

import android.content.Context
import android.os.Bundle
import com.techmesystem.intera.R
import com.techmesystem.intera.base.BaseActivity
import org.jetbrains.anko.startActivity

/**
 * Prem's creation, on 2020-02-08
 */
class CheckoutActivity : BaseActivity() {

  companion object {
    fun launch(from: Context?) = from?.run { startActivity<CheckoutActivity>() }
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_fragment_container)
    if (savedInstanceState == null) {
      val fragment = CheckoutFragment.newInstance(true)
      supportFragmentManager.beginTransaction().replace(R.id.fl_root_fragment_container, fragment, fragment.TAG).commit()
    }
  }
}