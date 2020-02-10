package com.techmesystem.intera.payment.view

import android.content.Context
import android.content.DialogInterface
import com.techmesystem.intera.R
import com.techmesystem.intera.base.bottomsheet.BaseBottomSheet
import com.techmesystem.intera.payment.component.ICardDataListener

/**
 * Prem's creation, on 2020-02-10
 */
class DeleteCardSuccessBottomSheet : BaseBottomSheet() {

  override val TAG: String = DeleteCardSuccessBottomSheet::class.java.name

  override fun layoutRes(): Int = R.layout.bottom_sheet_delete_card_success

  private lateinit var listener: ICardDataListener

  override fun onAttach(context: Context) {
    super.onAttach(context)
    listener = context as ICardDataListener
  }

  override fun onCancel(dialog: DialogInterface) {
    super.onCancel(dialog)
    listener.onCardDeleteFinish()
  }
}