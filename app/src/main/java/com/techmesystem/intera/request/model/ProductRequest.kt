package com.techmesystem.intera.request.model

import android.os.Parcelable
import com.techmesystem.intera.productdetail.data.PRODUCT_1
import com.techmesystem.intera.productdetail.data.PRODUCT_2
import com.techmesystem.intera.productdetail.model.Product
import kotlinx.android.parcel.Parcelize

/**
 * Prem's creation, on 2020-02-09
 */
@Parcelize data class ProductRequest(
  val id: String = "#7446005647478-01",
  val product1: Product = PRODUCT_1,
  val product2: Product = PRODUCT_2,
  val date: String = "01/01/2020",
  val totalAmount: String = "107,71",
  val cashback: String? = "50,00"
) : Parcelable