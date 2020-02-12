package com.techmesystem.intera.profile.model

import android.os.Parcelable
import com.techmesystem.intera.productdetail.model.Product
import kotlinx.android.parcel.Parcelize

/**
 * Prem's creation, on 2020-02-12
 */
@Parcelize data class ProductHistory(
  val cashback: String?, val productName: String, val companyName: String, val amountPaid: String, val product: Product
) : Parcelable {
  companion object {
    fun from(product: Product) = ProductHistory(
      product.cashback, product.shortOrFullCaption, product.companyName, product.priceAssociated, product
    )
  }
}