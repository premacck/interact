package com.techmesystem.intera.util

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.LayoutRes
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.core.view.isVisible
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.epoxy.DiffResult
import com.airbnb.epoxy.EpoxyController
import com.airbnb.epoxy.EpoxyRecyclerView
import com.airbnb.epoxy.OnModelBuildFinishedListener
import com.jakewharton.rxbinding2.view.RxView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import java.util.concurrent.TimeUnit

/**
 * Prem's creation, on 2020-02-05
 */
fun Context.findColor(@ColorRes resource: Int) = ContextCompat.getColor(this, resource)

fun Fragment.findColor(@ColorRes resource: Int) = requireContext().findColor(resource)

fun View.findColor(@ColorRes resource: Int) = context.findColor(resource)

fun Context.findDrawable(@DrawableRes drawableRes: Int): Drawable? = ContextCompat.getDrawable(this, drawableRes)

fun View.findDrawable(@DrawableRes drawableRes: Int): Drawable? = context.getDrawable(drawableRes)

fun View.onDebounceClick(delay: Long = 500L, action: () -> Unit): Disposable = RxView.clicks(this).let {
  it.throttleFirst(delay, TimeUnit.MILLISECONDS)
  it.observeOn(AndroidSchedulers.mainThread())
  it.subscribe({ action() }, { error -> Log.e("Error", "Error on click of", error) })
}

fun ViewGroup.attachLayout(@LayoutRes layoutRes: Int): View = RecyclerView.inflate(context, layoutRes, this)

fun Context.getJustLayout(@LayoutRes layoutRes: Int): View = RecyclerView.inflate(this, layoutRes, null)

fun View.disable(disableAlpha: Float = 0.2f) {
  if (isEnabled) {
    isEnabled = false
    isClickable = false
    alpha = disableAlpha
  }
}

fun View.enable() {
  if (!isEnabled) {
    isEnabled = true
    isClickable = true
    alpha = 1.0f
  }
}

fun View.enableOrDisable(enableCondition: Boolean, disableAlpha: Float = 0.2f) =
  if (enableCondition) enable() else disable(disableAlpha)

fun View.setMargins(left: Int? = null, top: Int? = null, right: Int? = null, bottom: Int? = null) {
  layoutParams = (layoutParams as? ViewGroup.MarginLayoutParams)?.apply {
    left?.let { leftMargin = it; }
    top?.let { topMargin = it }
    right?.let { rightMargin = it }
    bottom?.let { bottomMargin = it }
  }
}

fun View.hideView() {
  visibility = View.GONE
}

fun View.showView() {
  visibility = View.VISIBLE
}

fun View.invisibleView() {
  visibility = View.INVISIBLE
}

fun View.toggleVisibility() = showOrHide(!isVisible)

fun <T : View> T.showOrHide(showCondition: Boolean, actionIfShown: ((T) -> Unit)? = null) {
  if (showCondition) {
    showView()
    actionIfShown?.invoke(this)
  } else hideView()
}

fun <T : View> T.showOrInvisible(showCondition: Boolean, actionIfShown: ((T) -> Unit)? = null) {
  if (showCondition) {
    showView()
    actionIfShown?.invoke(this)
  } else invisibleView()
}

fun DrawerLayout.closeEnd() {
  if (isOpenEnd()) closeDrawer(GravityCompat.END)
}

fun DrawerLayout.openEnd() {
  if (!isOpenEnd()) openDrawer(GravityCompat.END)
}

fun DrawerLayout.isOpenEnd() = isDrawerOpen(GravityCompat.END)

fun DrawerLayout.toggleEnd() = if (isOpenEnd()) closeEnd() else openEnd()

fun TextView.setDrawables(start: Int = 0, top: Int = 0, end: Int = 0, bottom: Int = 0) = setCompoundDrawablesRelativeWithIntrinsicBounds(start, top, end, bottom)

fun View.setViewPadding(start: Int? = null, top: Int? = null, end: Int? = null, bottom: Int? = null) =
  setPaddingRelative(start ?: paddingStart, top ?: paddingTop, end ?: paddingEnd, bottom ?: paddingBottom)

fun EpoxyRecyclerView.buildModels(init: EpoxyController.() -> Unit) = buildModelsWith(object : EpoxyRecyclerView.ModelBuilderCallback {
  override fun buildModels(controller: EpoxyController) = controller.init()
})

fun EpoxyController.afterModelBuild(action: () -> Unit) {
  addModelBuildListener(object : OnModelBuildFinishedListener {
    override fun onModelBuildFinished(result: DiffResult) {
      removeModelBuildListener(this)
      action()
    }
  })
}