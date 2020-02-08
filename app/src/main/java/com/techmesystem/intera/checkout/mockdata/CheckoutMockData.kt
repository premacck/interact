package com.techmesystem.intera.checkout.mockdata

import com.techmesystem.intera.checkout.model.UserCart
import com.techmesystem.intera.productdetail.data.PRODUCT_1
import com.techmesystem.intera.productdetail.data.PRODUCT_1_EQUIVALENT
import com.techmesystem.intera.productdetail.data.PRODUCT_2
import com.techmesystem.intera.productdetail.data.PRODUCT_2_EQUIVALENT

/**
 * Prem's creation, on 2020-02-08
 */
val CART = UserCart(mutableListOf(PRODUCT_1, PRODUCT_2))

val CART_EQUIVALENT = UserCart(mutableListOf(PRODUCT_1_EQUIVALENT, PRODUCT_2_EQUIVALENT), false)