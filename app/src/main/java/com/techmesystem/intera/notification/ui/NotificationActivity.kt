package com.techmesystem.intera.notification.ui

import android.content.Context
import android.os.Bundle
import com.techmesystem.intera.R
import com.techmesystem.intera.base.BaseActivity
import com.techmesystem.intera.base.view.emptyItem
import com.techmesystem.intera.notification.mockdata.NOTIFICATIONS
import com.techmesystem.intera.notification.view.notificationItem
import com.techmesystem.intera.util.buildModels
import kotlinx.android.synthetic.main.view_list_with_toolbar.*
import org.jetbrains.anko.backgroundColorResource
import org.jetbrains.anko.startActivity

/**
 * Prem's creation, on 2020-02-10
 */
class NotificationActivity : BaseActivity() {

  private val list = ArrayList(NOTIFICATIONS)

  companion object {
    fun launch(from: Context?) = from?.run { startActivity<NotificationActivity>() }
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.view_list_with_toolbar)
    toolbar.onBackNavigation { onBackPressed() }
    cl_root.backgroundColorResource = R.color.color_f9f7ec
    erv_list.setItemSpacingDp(12)
    setNotificationList()
  }

  private fun setNotificationList() {
    erv_list.buildModels {
      if (list.isEmpty()) {
        emptyItem {
          id("EmptyItem for ${this@buildModels}")
          withErrorMessage(R.string.no_notifications)
        }
      } else {
        list.forEach { notification ->
          notificationItem {
            id(notification.toString())
            withNotification(notification)
            onRemoveClick {
              list.remove(it)
              setNotificationList()
            }
            onClick {  }
          }
        }
      }
    }
  }
}