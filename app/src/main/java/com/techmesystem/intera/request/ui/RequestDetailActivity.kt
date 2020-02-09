package com.techmesystem.intera.request.ui

import android.content.Context
import android.os.Bundle
import com.techmesystem.intera.R
import com.techmesystem.intera.base.BaseActivity
import com.techmesystem.intera.help.ui.HelpActivity
import com.techmesystem.intera.request.model.ProductRequest
import com.techmesystem.intera.util.onDebounceClick
import kotlinx.android.synthetic.main.activity_request_detail.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

/**
 * Prem's creation, on 2020-02-09
 */
class RequestDetailActivity : BaseActivity() {

  private lateinit var request: ProductRequest

  companion object {
    const val REQUEST = "request"
    fun launch(from: Context?, request: ProductRequest) = from?.run { startActivity<RequestDetailActivity>(REQUEST to request) }
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_request_detail)
    request = intent?.getParcelableExtra(REQUEST) ?: return
    toolbar.onBackNavigation { onBackPressed() }

    initView()
    initListeners()
  }

  private fun initView() {
    ri_request_item.withRequest(request)
  }

  private fun initListeners() {
    btn_ask_again.onDebounceClick {
      toast("ask again")
      NewOrderConfirmBottomSheet.newInstance(request).show(supportFragmentManager)
    }
    btn_help.onDebounceClick {
      toast("help")
      HelpActivity.launch(this)
    }
    btn_cancel.onDebounceClick { finish() }
  }
}