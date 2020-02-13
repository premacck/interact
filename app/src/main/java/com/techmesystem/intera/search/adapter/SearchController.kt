package com.techmesystem.intera.search.adapter

import com.airbnb.epoxy.TypedEpoxyController
import com.techmesystem.intera.R
import com.techmesystem.intera.base.view.headerItem
import com.techmesystem.intera.search.mockdata.POPULAR_SEARCH_LIST
import com.techmesystem.intera.search.model.ProductSearch
import com.techmesystem.intera.search.model.SearchItem
import com.techmesystem.intera.search.view.searchItemLayout
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

/**
 * Prem's creation, on 2020-02-13
 */
class SearchController(private val listener: Listener) : TypedEpoxyController<List<SearchItem>>() {

  private var popularSearchList = POPULAR_SEARCH_LIST
  private var searchTerm: String? = null

  fun filterBy(text: String?) {
    searchTerm = text
    if (text.isNullOrEmpty()) {
      popularSearchList = POPULAR_SEARCH_LIST
      setData(listener.totalSearchList)
    } else {
      popularSearchList = emptyList()
      doAsync {
        val filteredSearchTerms = listener.totalSearchList.filter { data -> data.text?.contains(text, true) == true }
        uiThread {
          setData(filteredSearchTerms)
        }
      }
    }
  }

  override fun buildModels(searchItems: List<SearchItem>?) {
    if (popularSearchList.isNotEmpty()) {
      headerItem {
        id("Most searched items")
        withText(R.string.most_searched_terms)
      }
    }
    popularSearchList.forEach { popularSearch ->
      searchItemLayout {
        id("${popularSearch.id}-${popularSearch.text}")
        withSearchItem(popularSearch)
        onClick { listener.onSearchItemClick(it) }
      }
    }
    if (popularSearchList.isEmpty()) {
      searchItems?.forEach { searchItem ->
        searchItemLayout {
          id("${searchItem.id}-${searchItem.text}")
          withHighlight(searchTerm)
          withSearchItem(searchItem)
          onClick { listener.onSearchItemClick(it) }
        }
      }
    }
  }

  interface Listener {
    val totalSearchList: MutableList<ProductSearch>

    fun onSearchItemClick(searchItem: SearchItem)
  }
}