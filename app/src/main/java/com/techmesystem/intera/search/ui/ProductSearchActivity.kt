package com.techmesystem.intera.search.ui

import android.content.Context
import android.os.Bundle
import androidx.annotation.IdRes
import androidx.annotation.StringRes
import com.techmesystem.intera.R
import com.techmesystem.intera.base.BaseActivity
import com.techmesystem.intera.filter.FilterContainer
import com.techmesystem.intera.filter.model.FilterFacets
import com.techmesystem.intera.filter.ui.FilterFragment
import com.techmesystem.intera.filter.ui.SortFragment
import com.techmesystem.intera.productdetail.model.Product
import com.techmesystem.intera.productdetail.ui.ProductDetailActivity
import com.techmesystem.intera.productdetail.view.productItem
import com.techmesystem.intera.search.ui.DepartmentActivity.Companion.LIST
import com.techmesystem.intera.search.ui.DepartmentActivity.Companion.TITLE
import com.techmesystem.intera.util.buildModels
import com.techmesystem.intera.util.closeEnd
import com.techmesystem.intera.util.onDebounceClick
import com.techmesystem.intera.util.openEnd
import kotlinx.android.synthetic.main.activity_product_search.*
import kotlinx.android.synthetic.main.content_product_search.*
import org.jetbrains.anko.startActivity

/**
 * Prem's creation, on 2020-02-13
 */
class ProductSearchActivity : BaseActivity(), FilterContainer {

  private lateinit var list: ArrayList<Product>
  private var pageType = R.string.filter
  private val filterFacets = FilterFacets()

  companion object {
    fun launch(from: Context?, @StringRes title: Int, list: ArrayList<Product>) = from?.run {
      startActivity<ProductSearchActivity>(TITLE to title, LIST to list)
    }
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_product_search)
    toolbar.onBackNavigation { onBackPressed() }
    intent?.getIntExtra(TITLE, R.string.intera)?.let { toolbar.title = getString(it) }
    list = intent?.getParcelableArrayListExtra(LIST) ?: return

    initListeners()
    initRecyclerView()
  }

  private fun initListeners() {
    tv_filter.onDebounceClick {
      openFilterPage(R.string.filter)
      dl_product_search?.openEnd()
    }
    tv_sort.onDebounceClick {
      openSortPage()
      dl_product_search?.openEnd()
    }
  }

  private fun initRecyclerView() {
    erv_search_items.buildModels {
      list.forEach { product ->
        productItem {
          id(product.toString())
          withProduct(product)
          showLeftMarker(true)
          showRightMarker(false)
          onClick { onProductItemClick(it) }
        }
      }
    }
  }

  private fun onProductItemClick(product: Product) {
    ProductDetailActivity.launch(this, product)
  }

  private fun openSortPage() {
    val sortFragment = SortFragment.newInstance(if (filterFacets.sortId == 0) R.id.rb_1 else filterFacets.sortId)
    supportFragmentManager.beginTransaction().replace(R.id.fl_product_search_drawer, sortFragment, sortFragment.TAG).commit()
  }

  private fun setSelectedValue(@StringRes title: Int, selectedValue: String?) {
    if (!selectedValue.isNullOrEmpty()) {
      when (title) {
        R.string.categories -> {
          filterFacets.categories.forEach { it.isSelected = false }
          filterFacets.categories.find { it.value == selectedValue }?.isSelected = true
        }
        R.string.brands -> {
          filterFacets.brands.forEach { it.isSelected = false }
          filterFacets.brands.find { it.value == selectedValue }?.isSelected = true
        }
        R.string.types_of_medicines -> {
          filterFacets.typesOfMedicines.forEach { it.isSelected = false }
          filterFacets.typesOfMedicines.find { it.value == selectedValue }?.isSelected = true
        }
      }
    }
  }

  private fun setSelectedValue(@IdRes viewId: Int) {
    filterFacets.sortId = viewId
  }

  override fun openFilterPage(pageType: Int, selectedValue: String?) {
    this.pageType = pageType
    val filterFragment = FilterFragment.newInstance(pageType == R.string.filter, pageType, filterFacets)
    supportFragmentManager.beginTransaction().replace(R.id.fl_product_search_drawer, filterFragment, filterFragment.TAG).commit()

    filterFragment.updateUi()
  }

  override fun onFilterClick(type: Int, value: String?) {
    if (type == R.string.filter) {
      openFilterPage(type, value)
    } else {
      setSelectedValue(type, value)
      dl_product_search?.closeEnd()
    }
  }

  override fun onSortClick(@IdRes viewId: Int) {
    setSelectedValue(viewId)
    dl_product_search?.closeEnd()
  }
}