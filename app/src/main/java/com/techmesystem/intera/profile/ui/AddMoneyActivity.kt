package com.techmesystem.intera.profile.ui

import android.content.Context
import android.os.Bundle
import com.techmesystem.intera.R
import com.techmesystem.intera.base.BaseActivity
import com.techmesystem.intera.util.onDebounceClick
import kotlinx.android.synthetic.main.activity_add_money.*
import org.jetbrains.anko.startActivity

/**
 * Prem's creation, on 2020-02-10
 */
class AddMoneyActivity : BaseActivity() {

  companion object {
    fun launch(from: Context?) = from?.run { startActivity<AddMoneyActivity>() }
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_add_money)
    toolbar.onBackNavigation { onBackPressed() }
    et_add_money.requestFocus()
    initListeners()
  }

  private fun initListeners() {
    btn_continue.onDebounceClick {
      InteraClubWelcomeBottomSheet().show(supportFragmentManager)
    }
  }
}