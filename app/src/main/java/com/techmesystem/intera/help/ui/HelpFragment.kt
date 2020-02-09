package com.techmesystem.intera.help.ui

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import com.techmesystem.intera.R
import com.techmesystem.intera.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_help.*

class HelpFragment : BaseFragment() {

  override val TAG: String = HelpFragment::class.java.name
  private var backNavigationEnabled: Boolean = false

  companion object {
    fun newInstance(backNavigationEnabled: Boolean = false) = HelpFragment().apply {
      arguments = bundleOf("backNavigationEnabled" to backNavigationEnabled)
    }
  }

  override fun layoutRes(): Int = R.layout.fragment_help

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    backNavigationEnabled = arguments?.getBoolean("backNavigationEnabled", false) ?: false
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    if (backNavigationEnabled) {
      toolbar.onBackNavigation { activity?.onBackPressed() }
    }
    toolbar.setBackNavigationEnabled(backNavigationEnabled, R.drawable.ic_back_arrow)
  }
}