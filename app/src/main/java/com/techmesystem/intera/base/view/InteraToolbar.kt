package com.techmesystem.intera.base.view

import android.content.Context
import android.util.AttributeSet
import android.view.Menu
import android.view.MenuItem
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import androidx.annotation.MenuRes
import androidx.appcompat.widget.Toolbar
import com.google.android.material.appbar.AppBarLayout
import com.techmesystem.intera.R
import com.techmesystem.intera.util.attachLayout
import com.techmesystem.intera.util.findColor
import kotlinx.android.synthetic.main.toolbar_intera.view.*
import org.jetbrains.anko.dip

/**
 * Prem's creation, on 2020-02-05
 */
class InteraToolbar @JvmOverloads constructor(
  context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : AppBarLayout(context, attrs, defStyleAttr) {

  private var isBackNavigationEnabled: Boolean = false
  private var navigationOnClickListener: (() -> Unit)? = null
  val toolbar: Toolbar
    get() = intera_toolbar
  var title: CharSequence?
    get() = tv_toolbar_title?.text
    set(value) {
      tv_toolbar_title?.text = value
    }

  init {
    attachLayout(R.layout.toolbar_intera)

    val array = context.obtainStyledAttributes(attrs, R.styleable.InteraToolbar)

    title = array.getString(R.styleable.InteraToolbar_toolbarTitle)
    setToolbarColor(
      array.getColor(R.styleable.InteraToolbar_backgroundColor, findColor(R.color.color_e5e5e5))
    )
    setTitleTextColor(
      array.getColor(R.styleable.InteraToolbar_titleTextColor, findColor(R.color.white))
    )
    array.getResourceId(R.styleable.InteraToolbar_backNavigationDrawable, -1).let {
      setBackNavigationEnabled(it != -1, it)
    }
    val menuRes = array.getResourceId(R.styleable.InteraToolbar_menuResource, -1)
    if (menuRes != -1) setMenu(menuRes)
    elevation(array.getFloat(R.styleable.InteraToolbar_toolbarElevation, dip(4).toFloat()))

    array.recycle()
  }

  fun elevation(viewElevation: Float) {
    elevation = viewElevation
  }

  fun onMenuClick(listener: (MenuItem) -> Boolean) {
    intera_toolbar?.setOnMenuItemClickListener { menuItem -> listener(menuItem) }
  }

  private fun setToolbarColor(@ColorInt color: Int) = intera_toolbar.setBackgroundColor(color)

  private fun setTitleTextColor(@ColorInt color: Int) = intera_toolbar.setTitleTextColor(color)

  private fun setMenu(@MenuRes menuRes: Int) = intera_toolbar.inflateMenu(menuRes)

  fun getMenu(): Menu? = intera_toolbar?.menu

  private fun setBackNavigationEnabled(enabled: Boolean, @DrawableRes backIcon: Int = R.drawable.ic_arrow_left_white) {
    isBackNavigationEnabled = enabled
    intera_toolbar?.run {
      if (enabled) {
        contentInsetStartWithNavigation = 0
        setNavigationIcon(backIcon)
        setNavigationOnClickListener { navigationOnClickListener?.invoke() }
      } else {
        navigationIcon = null
        navigationOnClickListener = null
        setNavigationOnClickListener(null)
      }
    }
  }

  fun onBackNavigation(action: () -> Unit) {
    navigationOnClickListener = action
  }
}