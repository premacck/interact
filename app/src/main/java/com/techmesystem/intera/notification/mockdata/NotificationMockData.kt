package com.techmesystem.intera.notification.mockdata

import com.techmesystem.intera.R
import com.techmesystem.intera.notification.model.InteraNotification
import com.techmesystem.intera.productdetail.data.PRODUCT_1
import com.techmesystem.intera.productdetail.data.PRODUCT_1_EQUIVALENT
import com.techmesystem.intera.productdetail.data.PRODUCT_2

/**
 * Prem's creation, on 2020-02-10
 */
val NOTIFICATIONS = arrayListOf(
  InteraNotification(R.string.sample_notification_title_1, PRODUCT_1, false),
  InteraNotification(R.string.sample_notification_title_2, PRODUCT_2, false),
  InteraNotification(R.string.sample_notification_title_3, PRODUCT_1_EQUIVALENT, true)
)