package com.techmesystem.intera.profile.mockdata

import com.techmesystem.intera.productdetail.data.PRODUCT_1
import com.techmesystem.intera.productdetail.data.PRODUCT_1_EQUIVALENT
import com.techmesystem.intera.productdetail.data.PRODUCT_2
import com.techmesystem.intera.productdetail.data.PRODUCT_2_EQUIVALENT
import com.techmesystem.intera.profile.model.ProductHistory

/**
 * Prem's creation, on 2020-02-12
 */
val HISTORY_LIST = listOf(
  ProductHistory.from(PRODUCT_1), ProductHistory.from(PRODUCT_2), ProductHistory.from(PRODUCT_1_EQUIVALENT), ProductHistory.from(PRODUCT_2_EQUIVALENT)
)