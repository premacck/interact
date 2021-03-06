package com.techmesystem.intera.auth.ui

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.techmesystem.intera.R
import com.techmesystem.intera.base.BaseActivity
import com.techmesystem.intera.home.ui.HomeActivity
import com.techmesystem.intera.profile.ui.AddressActivity
import com.techmesystem.intera.profile.ui.AddressActivity.Companion.REQUEST_REGISTER
import com.techmesystem.intera.util.onDebounceClick
import com.techmesystem.intera.util.showView
import kotlinx.android.synthetic.main.fragment_personal_data.*
import org.jetbrains.anko.startActivity

/**
 * Prem's creation, on 2020-02-11
 */
class RegisterActivity : BaseActivity() {

  companion object {
    fun launch(from: Context?) = from?.run { startActivity<RegisterActivity>() }
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_register)
    initViews()
    initListeners()
  }

  private fun initViews() {
    tv_i_agree_to_terms_of_use.showView()
    tv_i_use_continuous_medication.showView()
    tv_continuous_medication_usage_message.showView()
  }

  private fun initListeners() {
    btn_continue.onDebounceClick { /* TODO: open address */ }
    tv_i_agree_to_terms_of_use.onDebounceClick {
      tv_i_agree_to_terms_of_use.isChecked = !tv_i_agree_to_terms_of_use.isChecked
    }
    tv_i_use_continuous_medication.onDebounceClick {
      tv_i_use_continuous_medication.isChecked = !tv_i_use_continuous_medication.isChecked
    }
    btn_continue.onDebounceClick {
      AddressActivity.launchForResult(this)
    }
  }

  override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    super.onActivityResult(requestCode, resultCode, data)
    if (requestCode == REQUEST_REGISTER && resultCode == Activity.RESULT_OK) {
      HomeActivity.launch(this)
      finish()
    }
  }
}