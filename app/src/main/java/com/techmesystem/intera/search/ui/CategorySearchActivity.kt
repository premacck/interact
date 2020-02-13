package com.techmesystem.intera.search.ui

import android.content.Context
import android.os.Bundle
import com.techmesystem.intera.R
import com.techmesystem.intera.base.BaseActivity
import com.techmesystem.intera.productdetail.ui.ProductDetailActivity
import com.techmesystem.intera.search.adapter.SearchController
import com.techmesystem.intera.search.mockdata.SEARCH_LIST
import com.techmesystem.intera.search.model.PopularSearch
import com.techmesystem.intera.search.model.ProductSearch
import com.techmesystem.intera.search.model.SearchItem
import com.techmesystem.intera.util.onDebounceClick
import com.techmesystem.intera.util.searchOnTextChange
import kotlinx.android.synthetic.main.activity_search.*
import org.jetbrains.anko.startActivity

/**
 * Prem's creation, on 2020-02-13
 */
class CategorySearchActivity : BaseActivity(), SearchController.Listener {

  private lateinit var controller: SearchController

  private val searchList: MutableList<ProductSearch> = SEARCH_LIST
  override val totalSearchList: MutableList<ProductSearch>
    get() = searchList

  companion object {
    fun launch(from: Context?) = from?.run { startActivity<CategorySearchActivity>() }
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_search)
    toolbar.onBackNavigation { onBackPressed() }
    initListeners()
    initRecyclerView()
  }

  private fun initListeners() {
    et_search.searchOnTextChange(disposable) { controller.filterBy(it) }
    btn_scan_bar_code.onDebounceClick {  }
    btn_search.onDebounceClick {  }
  }

  private fun initRecyclerView() {
    controller = SearchController(this)
    erv_search_items.setController(controller)
    controller.setData(searchList)
  }

  override fun onSearchItemClick(searchItem: SearchItem) {
    when (searchItem) {
      is PopularSearch -> {
      }
      is ProductSearch -> {
        ProductDetailActivity.launch(this, searchItem.product)
      }
    }
  }
}
