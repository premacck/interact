package com.techmesystem.intera.profile.ui

import android.os.Bundle
import android.view.View
import com.techmesystem.intera.R
import com.techmesystem.intera.base.BaseFragment
import com.techmesystem.intera.favorite.ui.FavoriteActivity
import com.techmesystem.intera.notification.ui.NotificationActivity
import com.techmesystem.intera.payment.ui.FormOfPaymentActivity
import com.techmesystem.intera.payment.ui.FormOfPaymentWithProductActivity
import com.techmesystem.intera.request.ui.RequestsActivity
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
    tv_profile_name?.text = getString(R.string.hi_x, "Bruno")
    tv_interacash_balance?.text = getString(R.string.dollar, "10,00")
  }

  private fun initListeners() {
    btn_extract?.onDebounceClick { ProductHistoryActivity.launch(context) }
    btn_add?.onDebounceClick { AddMoneyActivity.launch(context) }
    tv_interacash_balance?.onDebounceClick { FormOfPaymentWithProductActivity.launch(context) }
    tv_edit_interacash_balance?.onDebounceClick { FormOfPaymentWithProductActivity.launch(context) }

    tv_personal_data?.onDebounceClick { PersonalDataActivity.launch(context) }
    tv_requests?.onDebounceClick { RequestsActivity.launch(context) }
    tv_favorites?.onDebounceClick { FavoriteActivity.launch(context) }
    tv_notifications?.onDebounceClick { NotificationActivity.launch(context) }
    tv_forms_of_payment?.onDebounceClick { FormOfPaymentActivity.launch(context) }
    tv_allow_fingerprint?.onDebounceClick {  }
    tv_qualidoc?.onDebounceClick {  }
    tv_refer_a_friend?.onDebounceClick {  }
    tv_sweepstakes?.onDebounceClick {  }
    tv_cancel_account?.onDebounceClick {  }
  }
}