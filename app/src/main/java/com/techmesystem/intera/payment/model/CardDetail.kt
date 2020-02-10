package com.techmesystem.intera.payment.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Prem's creation, on 2020-02-10
 */
@Parcelize data class CardDetail(
  var cardNumber: String = "", var cardCvv: String = "", var nameOnCard: String = "", var cardMonth: String = "", var cardYear: String = ""
) : Parcelable {

  companion object {
    fun mock() = CardDetail("•••• •••• •••• 7055", "890", "Sebastian Green", "Julho", "2024")
  }

  fun isEmpty() = cardNumber.isEmpty() && cardCvv.isEmpty() && nameOnCard.isEmpty() && cardMonth.isEmpty() && cardYear.isEmpty()
}