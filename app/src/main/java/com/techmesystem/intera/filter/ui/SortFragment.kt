package com.techmesystem.intera.filter.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import com.techmesystem.intera.R
import com.techmesystem.intera.base.BaseFragment
import com.techmesystem.intera.filter.FilterContainer
import com.techmesystem.intera.util.onDebounceClick
import kotlinx.android.synthetic.main.fragment_sort.*
import org.jetbrains.anko.textResource

/**
 * Prem's creation, on 2020-02-14
 */
class SortFragment : BaseFragment() {

  override val TAG: String = SortFragment::class.java.name

  private var sortId = R.id.rb_1
  private lateinit var container: FilterContainer

  companion object {
    private const val SORT_ID = "sortId"
    fun newInstance(sortId: Int) = SortFragment().apply {
      arguments = bundleOf(SORT_ID to sortId)
    }
  }

  override fun layoutRes(): Int = R.layout.fragment_sort

  override fun onAttach(context: Context) {
    super.onAttach(context)
    container = context as FilterContainer
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    sortId = arguments?.getInt(SORT_ID) ?: R.id.rb_1
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    tv_title?.textResource = R.string.order_by
    rg_sort?.selectViewProgrammatically(sortId)
    initListeners()
  }

  private fun initListeners() {
    rb_1?.onDebounceClick { container.onSortClick(R.id.rb_1) }
    rb_2?.onDebounceClick { container.onSortClick(R.id.rb_2) }
    rb_3?.onDebounceClick { container.onSortClick(R.id.rb_3) }
    rb_4?.onDebounceClick { container.onSortClick(R.id.rb_4) }
  }
}