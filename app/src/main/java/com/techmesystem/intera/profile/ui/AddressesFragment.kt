package com.techmesystem.intera.profile.ui

import android.os.Bundle
import android.view.View
import com.techmesystem.intera.R
import com.techmesystem.intera.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_addresses.*
import org.jetbrains.anko.support.v4.toast

/**
 * prem's creation, on 2020-02-09
 */
class AddressesFragment : BaseFragment() {

  override val TAG: String = AddressesFragment::class.java.name

  override fun layoutRes(): Int = R.layout.fragment_addresses

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    ctv_address_1.onClick { toast("Address 1 : $it") }
  }
}