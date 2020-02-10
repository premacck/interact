package com.techmesystem.intera.profile.ui

import android.app.Activity
import android.os.Bundle
import com.techmesystem.intera.R
import com.techmesystem.intera.base.BaseActivity
import com.techmesystem.intera.util.onDebounceClick
import kotlinx.android.synthetic.main.activity_address.*
import org.jetbrains.anko.startActivityForResult

/**
 * Prem's creation, on 2020-02-11
 */
class AddressActivity : BaseActivity() {

  companion object {
    const val REQUEST_REGISTER = 2
    fun launchForResult(from: Activity?) = from?.run { startActivityForResult<AddressActivity>(REQUEST_REGISTER) }
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_address)
    btn_continue.onDebounceClick {
      setResult(Activity.RESULT_OK)
      finish()
    }
  }
}