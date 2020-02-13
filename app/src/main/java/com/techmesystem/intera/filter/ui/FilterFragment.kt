package com.techmesystem.intera.filter.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.annotation.StringRes
import androidx.core.os.bundleOf
import com.airbnb.epoxy.EpoxyController
import com.techmesystem.intera.R
import com.techmesystem.intera.base.BaseFragment
import com.techmesystem.intera.base.view.customTextView
import com.techmesystem.intera.filter.FilterContainer
import com.techmesystem.intera.filter.mockdata.FILTER_LIST
import com.techmesystem.intera.filter.model.FilterFacets
import com.techmesystem.intera.util.buildModels
import com.techmesystem.intera.util.onDebounceClick
import com.techmesystem.intera.util.showOrHide
import kotlinx.android.synthetic.main.fragment_filter.*
import org.jetbrains.anko.textResource

/**
 * Prem's creation, on 2020-02-14
 */
class FilterFragment : BaseFragment() {

  override val TAG: String = FilterFragment::class.java.name

  private lateinit var container: FilterContainer
  private var isFirstFragment: Boolean = true
  @StringRes var title: Int = R.string.filter
  private lateinit var filterFacets: FilterFacets

  companion object {
    private const val IS_FIRST_FRAGMENT = "isFirstFragment"
    private const val TITLE = "title"
    private const val FACETS = "FACETS"
    fun newInstance(isFirstFragment: Boolean, title: Int, filterFacets: FilterFacets) = FilterFragment().apply {
      arguments = bundleOf(IS_FIRST_FRAGMENT to isFirstFragment, TITLE to title, FACETS to filterFacets)
    }
  }

  override fun onAttach(context: Context) {
    super.onAttach(context)
    container = context as FilterContainer
  }

  override fun layoutRes(): Int = R.layout.fragment_filter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    isFirstFragment = arguments?.getBoolean(IS_FIRST_FRAGMENT) ?: true
    title = arguments?.getInt(TITLE) ?: R.string.filter
    filterFacets = arguments?.getParcelable(FACETS) ?: return
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    updateUi()
  }

  fun updateUi() {
    initViews()
    initRecyclerView()
  }

  private fun initViews() {
    tv_title?.textResource = title
    btn_go_back?.showOrHide(!isFirstFragment) {
      it.onDebounceClick { container.onFilterClick(R.string.filter) }
    }
    v_bottom_line?.showOrHide(!isFirstFragment)
  }

  private fun initRecyclerView() {
    erv_filter_list?.buildModels {
      when (title) {
        R.string.categories -> showCategoriesPage(filterFacets)
        R.string.brands -> showBrandsPage(filterFacets)
        R.string.types_of_medicines -> showTypesOfMedicinesPage(filterFacets)
        R.string.price_range -> {
          // Do nothing as of now
        }
        else -> showFilterRootPage()
      }
    }
  }

  private fun EpoxyController.showCategoriesPage(filterFacets: FilterFacets) {
    filterFacets.categories.forEach { category ->
      customTextView {
        id(category.toString())
        mainText(category.value)
        withHorizontalDrawables(Pair(0, if (category.isSelected) R.drawable.ic_check_dark_small else 0))
        onClick { container.onFilterClick(R.string.categories, it) }
      }
    }
  }

  private fun EpoxyController.showBrandsPage(filterFacets: FilterFacets) {
    filterFacets.brands.forEach { category ->
      customTextView {
        id(category.toString())
        mainText(category.value)
        withHorizontalDrawables(Pair(0, if (category.isSelected) R.drawable.ic_check_dark_small else 0))
        onClick { container.onFilterClick(R.string.brands, it) }
      }
    }
  }

  private fun EpoxyController.showTypesOfMedicinesPage(filterFacets: FilterFacets) {
    filterFacets.typesOfMedicines.forEach { category ->
      customTextView {
        id(category.toString())
        mainText(category.value)
        withHorizontalDrawables(Pair(0, if (category.isSelected) R.drawable.ic_check_dark_small else 0))
        onClick { container.onFilterClick(R.string.types_of_medicines, it) }
      }
    }
  }

  private fun EpoxyController.showFilterRootPage() {
    FILTER_LIST.forEach { filter ->
      customTextView {
        id(filter)
        mainText(filter)
        withHorizontalDrawables(Pair(0, R.drawable.ic_arrow_right_grey))
        onClick { container.openFilterPage(filter) }
      }
    }
  }
}
