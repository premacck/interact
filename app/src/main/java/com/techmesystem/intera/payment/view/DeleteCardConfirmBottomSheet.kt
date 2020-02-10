package com.techmesystem.intera.payment.view

import android.content.Context
import com.techmesystem.intera.R
import com.techmesystem.intera.base.bottomsheet.InteraBottomSheet
import com.techmesystem.intera.payment.component.ICardDataListener

/**
 * Prem's creation, on 2020-02-10
 */
class DeleteCardConfirmBottomSheet : InteraBottomSheet() {

  override val TAG: String
    get() = DeleteCardConfirmBottomSheet::class.java.name
  override val title: CharSequence?
    get() = getString(R.string.are_you_sure_you_want_to_delete_card)
  override val message: CharSequence?
    get() = null
  override val positiveButtonText: CharSequence?
    get() = getString(R.string.no)
  override val positiveButtonAction: () -> Unit
    get() = { dismiss() }
  override val negativeButtonText: CharSequence?
    get() = getString(R.string.yes)
  override val negativeButtonAction: () -> Unit
    get() = {
      dismiss()
      listener.onCardDeleteDone()
    }

  private lateinit var listener: ICardDataListener

  override fun onAttach(context: Context) {
    super.onAttach(context)
    listener = context as ICardDataListener
  }
}