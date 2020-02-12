package com.techmesystem.intera.profile.adapter

import com.airbnb.epoxy.TypedEpoxyController
import com.techmesystem.intera.productdetail.model.Product
import com.techmesystem.intera.profile.model.ProductHistory
import com.techmesystem.intera.profile.view.extractInteracashItem
import com.techmesystem.intera.profile.view.productHistoryItem

/**
 * Prem's creation, on 2020-02-12
 */
class ProductHistoryController(private val listener: Listener) : TypedEpoxyController<List<ProductHistory>>() {

  var selectedYear: String? = null
  var selectedMonth: String? = null

  override fun buildModels(historyList: List<ProductHistory>?) {
    extractInteracashItem {
      id("History range picker item - ${selectedMonth}/${selectedYear} **** Results = $historyList")
      withMonth(selectedMonth)
      withYear(selectedYear)
      onMonthClick { listener.selectAndSetMonth() }
      onYearClick { listener.selectAndSetYear() }
    }
    historyList?.forEach { history ->
      productHistoryItem {
        id(history.toString())
        withHistory(history)
        onClick { listener.onProductClick(it) }
      }
    }
  }

  interface Listener {

    fun onProductClick(product: Product)

    fun selectAndSetMonth()

    fun selectAndSetYear()
  }
}