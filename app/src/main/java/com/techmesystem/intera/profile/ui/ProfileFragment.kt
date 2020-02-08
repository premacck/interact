package com.techmesystem.intera.profile.ui

import android.os.Bundle
import android.view.View
import com.techmesystem.intera.R
import com.techmesystem.intera.base.BaseFragment
import com.techmesystem.intera.util.onDebounceClick
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment : BaseFragment() {

  override val TAG: String = ProfileFragment::class.java.name

  override fun layoutRes(): Int = R.layout.fragment_profile

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    initView()
    initListeners()
  }

  private fun initView() {
    tv_profile_name.text = getString(R.string.hi_x, "Bruno")
    tv_interacash_balance.text = getString(R.string.dollar, "10,00")
  }

  private fun initListeners() {
    tv_allow_fingerprint?.onDebounceClick {  }
    tv_qualidoc?.onDebounceClick {  }
    tv_refer_a_friend?.onDebounceClick {  }
    tv_notifications?.onDebounceClick {  }
    tv_favorites?.onDebounceClick {  }
    tv_forms_of_payment?.onDebounceClick {  }
    tv_sweepstakes?.onDebounceClick {  }
    tv_requests?.onDebounceClick {  }
    tv_personal_data?.onDebounceClick {  }
    tv_cancel_account?.onDebounceClick {  }
  }
}