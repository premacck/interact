package com.techmesystem.intera.payment.mockdata

import java.util.*

/**
 * Prem's creation, on 2020-02-10
 */
class YearList : ArrayList<String>() {
  init {
    val calendar = Calendar.getInstance()
    val currentYear = calendar.get(Calendar.YEAR)
    addAll((currentYear .. currentYear + 5).map { it.toString() })
  }
}