package com.techmesystem.intera.base

import android.os.Bundle
import android.view.View
import androidx.annotation.CallSuper
import com.techmesystem.intera.R
import com.techmesystem.intera.base.view.InteraToolbar

/**
 * Prem's creation, on 2020-02-10
 */
abstract class ContainerFragment : BaseFragment() {

  private var backNavigationEnabled: Boolean = false
  abstract val toolbarLayout: InteraToolbar?

  companion object {
    const val BACK_NAVIGATION_ENABLED = "backNavigationEnabled"
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    backNavigationEnabled = arguments?.getBoolean(BACK_NAVIGATION_ENABLED, false)
        ?: throw IllegalArgumentException("backNavigationEnabled must be set with BACK_NAVIGATION_ENABLED key on a class extending ContainerFragment")
  }

  @CallSuper override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    if (backNavigationEnabled) {
      toolbarLayout?.onBackNavigation { activity?.onBackPressed() }
    }
    toolbarLayout?.setBackNavigationEnabled(backNavigationEnabled, R.drawable.ic_back_arrow)
  }
}