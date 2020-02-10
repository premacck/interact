package com.techmesystem.intera.help.ui

import androidx.core.os.bundleOf
import com.techmesystem.intera.R
import com.techmesystem.intera.base.ContainerFragment
import com.techmesystem.intera.base.view.InteraToolbar
import kotlinx.android.synthetic.main.fragment_help.*

class HelpFragment : ContainerFragment() {

  override val TAG: String = HelpFragment::class.java.name

  companion object {
    fun newInstance(backNavigationEnabled: Boolean = false) = HelpFragment().apply {
      arguments = bundleOf(BACK_NAVIGATION_ENABLED to backNavigationEnabled)
    }
  }

  override val toolbarLayout: InteraToolbar?
    get() = toolbar

  override fun layoutRes(): Int = R.layout.fragment_help
}