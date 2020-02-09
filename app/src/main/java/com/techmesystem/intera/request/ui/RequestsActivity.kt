package com.techmesystem.intera.request.ui

import android.content.Context
import android.os.Bundle
import com.techmesystem.intera.R
import com.techmesystem.intera.base.BaseActivity
import com.techmesystem.intera.help.ui.HelpActivity
import com.techmesystem.intera.request.mockdata.REQUESTS
import com.techmesystem.intera.request.model.ProductRequest
import com.techmesystem.intera.request.view.requestItem
import com.techmesystem.intera.util.buildModels
import kotlinx.android.synthetic.main.view_list_with_toolbar.*
import org.jetbrains.anko.startActivity

/**
 * Prem's creation, on 2020-02-09
 */
class RequestsActivity : BaseActivity() {

  companion object {
    fun launch(from: Context?) = from?.run { startActivity<RequestsActivity>() }
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.view_list_with_toolbar)
    initRecyclerView()
  }

  private fun initRecyclerView() {
    val list = REQUESTS
    erv_list.buildModels {
      list.forEach { request ->
        requestItem {
          id(request.toString())
          withRequest(request)
          onDetailClick { onDetailClick(it) }
          onAskAgainClick { onAskAgainClick(it) }
          onHelpClick { onHelpClick() }
        }
      }
    }
  }

  private fun onDetailClick(request: ProductRequest) = RequestDetailActivity.launch(this, request)

  private fun onAskAgainClick(request: ProductRequest) = NewOrderConfirmBottomSheet.newInstance(request).show(supportFragmentManager)

  private fun onHelpClick() = HelpActivity.launch(this)
}