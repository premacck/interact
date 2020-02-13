package com.techmesystem.intera.search.view

import android.content.Context
import android.util.AttributeSet
import androidx.annotation.DrawableRes
import androidx.constraintlayout.widget.ConstraintLayout
import com.airbnb.epoxy.ModelProp
import com.airbnb.epoxy.ModelProp.Option.DoNotHash
import com.airbnb.epoxy.ModelView
import com.techmesystem.intera.R
import com.techmesystem.intera.search.model.SearchItem
import com.techmesystem.intera.util.*
import kotlinx.android.synthetic.main.item_search.view.*
import org.jetbrains.anko.*

/**
 * Prem's creation, on 2020-02-13
 */
@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT) class SearchItemLayout @JvmOverloads constructor(
  context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

  private lateinit var searchItem: SearchItem

  init {
    attachLayout(R.layout.item_search)
    horizontalPadding = dip(32)
    verticalPadding = dip(14)
    backgroundResource = R.drawable.ripple_rect
  }

  @ModelProp(DoNotHash) fun withSearchItem(searchItem: SearchItem) {
    this.searchItem = searchItem
    withText(searchItem.text)
    withIcon(searchItem.icon ?: 0)
  }

  private fun withText(string: CharSequence?) {
    tv_search_name?.text = string
  }

  private fun withIcon(@DrawableRes icon: Int = 0) {
    iv_search_thumbnail?.showOrHide(icon != 0) {
      it.imageResource = icon
    }
  }

  @JvmOverloads @ModelProp fun withHighlight(subString: CharSequence? = null) {
    if (subString == null) return
    tv_search_name.text = tv_search_name.text.toString().makeSectionOfTextBoldPlusColor(
      subString.toString(), findColor(R.color.color_333333)
    )
  }

  @ModelProp(DoNotHash) fun onClick(action: (SearchItem) -> Unit) = onDebounceClick { action(searchItem) }
}