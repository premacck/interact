package com.techmesystem.intera.request.mockdata

import com.techmesystem.intera.productdetail.data.PRODUCT_1_EQUIVALENT
import com.techmesystem.intera.productdetail.data.PRODUCT_2_EQUIVALENT
import com.techmesystem.intera.request.model.ProductRequest

/**
 * Prem's creation, on 2020-02-09
 */
val REQUESTS = mutableListOf(
  ProductRequest(),
  ProductRequest(product1 = PRODUCT_1_EQUIVALENT, product2 = PRODUCT_2_EQUIVALENT),
  ProductRequest(),
  ProductRequest(product1 = PRODUCT_1_EQUIVALENT, product2 = PRODUCT_2_EQUIVALENT)
)