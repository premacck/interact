package com.techmesystem.intera.base.view

import android.content.Context
import android.util.AttributeSet
import android.widget.CompoundButton
import android.widget.RadioButton
import androidx.constraintlayout.widget.ConstraintHelper
import androidx.constraintlayout.widget.ConstraintLayout

/**
 * Prem's creation, on 2020-02-08
 */
class FlatRadioGroup @JvmOverloads constructor(
  context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintHelper(context, attrs, defStyleAttr) {

  private val radioViews = ArrayList<RadioButton>()
  private var skipCheckingViewsRecursively = false
  private var currentSelectedView: Int = -1
  private var viewSelectedBeforePreLayout: Int = -1

  private val childCheckChangeListener = CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->
    if (skipCheckingViewsRecursively) {
      return@OnCheckedChangeListener
    }
    if (currentSelectedView != -1) {
      skipCheckingViewsRecursively = true
      for (view in radioViews) {
        if (view.id == currentSelectedView) {
          view.isChecked = false
          break
        }
      }
      skipCheckingViewsRecursively = false
    }
    currentSelectedView = buttonView.id
  }

  override fun init(attrs: AttributeSet?) {
    super.init(attrs)
    this.mUseViewMeasure = false
  }

  override fun updatePreLayout(container: ConstraintLayout) {
    for (i in 0 until this.mCount) {
      val id = this.mIds[i]
      val view = container.getViewById(id)
      if (view != null && view is RadioButton) {
        radioViews.add(view)
        view.setOnCheckedChangeListener(childCheckChangeListener)
      }
    }

    if (viewSelectedBeforePreLayout != -1) selectViewProgrammatically(viewSelectedBeforePreLayout)
  }

  override fun updatePostLayout(container: ConstraintLayout?) {
    val params = this.layoutParams as ConstraintLayout.LayoutParams
    params.width = 0
    params.height = 0
  }

  fun clearSelection() {
    if (currentSelectedView != -1) {
      skipCheckingViewsRecursively = true
      for (view in radioViews) {
        if (view.id == currentSelectedView) {
          view.isChecked = false
          break
        }
      }
      skipCheckingViewsRecursively = false
      currentSelectedView = -1
    }
  }

  fun selectViewProgrammatically(buttonViewId: Int) {
    viewSelectedBeforePreLayout = if (radioViews.isEmpty()) buttonViewId else -1

    skipCheckingViewsRecursively = true
    for (view in radioViews) {
      view.isChecked = view.id == buttonViewId
    }
    skipCheckingViewsRecursively = false

    currentSelectedView = buttonViewId

  }
}