package com.techmesystem.intera.search.adapter

import com.airbnb.epoxy.TypedEpoxyController
import com.techmesystem.intera.base.view.simpleTextLayout
import org.jetbrains.anko.*

/**
 * Prem's creation, on 2020-02-13
 */
class DepartmentController(private val listener: Listener) : TypedEpoxyController<List<String>>() {

  private var searchTerm: String? = null

  fun filterBy(text: String?) {
    searchTerm = text
    if (text.isNullOrEmpty()) {
      setData(listener.totalSearchList)
    } else {
      doAsync {
        val filteredSearchTerms = listener.totalSearchList.filter { data -> data.contains(text, true) }
        uiThread {
          setData(filteredSearchTerms)
        }
      }
    }
  }

  override fun buildModels(list: List<String>?) {
    list?.forEach { item ->
      simpleTextLayout {
        id(item)
        withText(item)
        withConfig {
          it.verticalPadding = it.dip(14)
          it.horizontalPadding = it.dip(32)
        }
        isCardVisible(false)
        onClick { listener.onDepartmentItemClick(it) }
      }
    }
  }

  interface Listener {

    val totalSearchList: MutableList<String>

    fun onDepartmentItemClick(text: String)
  }
}