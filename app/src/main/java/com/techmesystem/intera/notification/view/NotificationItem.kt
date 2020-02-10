@file:Suppress("DEPRECATION")

package com.techmesystem.intera.notification.view

import android.animation.AnimatorInflater
import android.content.Context
import android.text.Html
import android.util.AttributeSet
import androidx.cardview.widget.CardView
import com.airbnb.epoxy.ModelProp
import com.airbnb.epoxy.ModelProp.Option.DoNotHash
import com.airbnb.epoxy.ModelView
import com.techmesystem.intera.R
import com.techmesystem.intera.notification.model.InteraNotification
import com.techmesystem.intera.util.attachLayout
import com.techmesystem.intera.util.onDebounceClick
import kotlinx.android.synthetic.main.item_notification.view.*
import org.jetbrains.anko.dip
import org.jetbrains.anko.imageResource

/**
 * Prem's creation, on 2020-02-10
 */
@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT) class NotificationItem @JvmOverloads constructor(
  context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : CardView(context, attrs, defStyleAttr) {

  private lateinit var notification: InteraNotification

  init {
    attachLayout(R.layout.item_notification)
    radius = dip(6).toFloat()
    cardElevation = dip(8).toFloat()
    stateListAnimator = AnimatorInflater.loadStateListAnimator(context, R.animator.scale_on_touch)
  }

  @ModelProp fun withNotification(notification: InteraNotification) {
    this.notification = notification
    tv_title?.text = Html.fromHtml(context.getString(notification.title))
    iv_product_thumbnail?.imageResource = notification.icon
    tv_product_caption?.text = notification.productCaption
    tv_product_company?.text = notification.productCompany
    isNotificationRead(notification.isRead)
  }

  private fun isNotificationRead(isRead: Boolean) {
    alpha = if (isRead) 0.6f else 1f
  }

  @ModelProp(DoNotHash) fun onRemoveClick(action: (notification: InteraNotification) -> Unit) = btn_remove?.onDebounceClick { action(notification) }

  @ModelProp(DoNotHash) fun onClick(action: (notification: InteraNotification) -> Unit) = onDebounceClick { action(notification) }
}