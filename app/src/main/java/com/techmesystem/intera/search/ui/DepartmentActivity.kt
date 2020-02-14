package com.techmesystem.intera.search.ui

import android.content.Context
import android.os.Bundle
import androidx.annotation.StringRes
import com.techmesystem.intera.R
import com.techmesystem.intera.base.BaseActivity
import com.techmesystem.intera.search.adapter.DepartmentController
import com.techmesystem.intera.search.mockdata.DEPARTMENT_AUTOCUIDADO_LIST
import com.techmesystem.intera.search.mockdata.DEPARTMENT_MEDICAMENTOS_LIST
import com.techmesystem.intera.search.mockdata.PRODUCT_SEARCH_LIST
import com.techmesystem.intera.util.onDebounceClick
import com.techmesystem.intera.util.searchOnTextChange
import kotlinx.android.synthetic.main.activity_search.*
import org.jetbrains.anko.startActivity

/**
 * Prem's creation, on 2020-02-13
 */
class DepartmentActivity : BaseActivity(), DepartmentController.Listener {

  private lateinit var controller: DepartmentController
  private lateinit var list: ArrayList<String>
  override val totalSearchList: MutableList<String>
    get() = list

  companion object {
    const val TITLE = "title"
    const val LIST = "list"
    fun launch(from: Context?, @StringRes title: Int, list: ArrayList<String>) = from?.run {
      startActivity<DepartmentActivity>(TITLE to title, LIST to list)
    }
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_search)
    toolbar.onBackNavigation { onBackPressed() }
    intent?.getIntExtra(TITLE, R.string.intera)?.let { toolbar.title = getString(it) }
    list = intent?.getStringArrayListExtra(LIST) ?: return

    initListeners()
    initRecyclerView()
  }

  private fun initListeners() {
    et_search.searchOnTextChange(disposable) { controller.filterBy(it) }
    btn_scan_bar_code.onDebounceClick { BarcodeScannerActivity.launch(this) }
    btn_search.onDebounceClick { }
  }

  private fun initRecyclerView() {
    controller = DepartmentController(this)
    erv_search_items.setItemSpacingPx(0)
    erv_search_items.setController(controller)
    controller.setData(list)
  }

  override fun onDepartmentItemClick(text: String) {
    when (text) {
      "Medicamentos" -> launch(this, R.string.medicines, DEPARTMENT_MEDICAMENTOS_LIST)
      "Autocuidado" -> launch(this, R.string.self_care, DEPARTMENT_AUTOCUIDADO_LIST)
      "Alergia" -> ProductSearchActivity.launch(this, R.string.allergy, PRODUCT_SEARCH_LIST)
      "Todos de A-Z", "Ver tudo" -> ProductSearchActivity.launch(this, R.string.most_searched_products, PRODUCT_SEARCH_LIST)
      else -> ProductSearchActivity.launch(this, R.string.equivalent_products, PRODUCT_SEARCH_LIST)
    }
  }
}
