package com.techmesystem.intera.profile.ui

import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.google.android.material.tabs.TabLayout
import com.techmesystem.intera.R
import com.techmesystem.intera.base.bottomsheet.BaseBottomSheet
import com.techmesystem.intera.util.enableOrDisable
import kotlinx.android.synthetic.main.bottom_sheet_intera_club_welcome.*

/**
 * Prem's creation, on 2020-02-11
 */
class InteraClubWelcomeBottomSheet : BaseBottomSheet() {

  override val TAG: String = InteraClubWelcomeBottomSheet::class.java.name

  private val onlyAssociatedViews: Array<TextView> by lazy { arrayOf(view_3, view_4, view_5, view_6, view_7) }

  override fun layoutRes(): Int = R.layout.bottom_sheet_intera_club_welcome

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    tl_club_type?.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
      override fun onTabReselected(tab: TabLayout.Tab?) {}
      override fun onTabUnselected(tab: TabLayout.Tab?) {}
      override fun onTabSelected(tab: TabLayout.Tab?) {
        onlyAssociatedViews.forEach { it.enableOrDisable(tab?.position == 0) }
      }
    })
  }
}