package com.techmesystem.intera.favorite.ui

import android.os.Bundle
import android.view.View
import com.techmesystem.intera.R
import com.techmesystem.intera.base.BaseFragment
import com.techmesystem.intera.checkout.view.productCheckoutItem
import com.techmesystem.intera.favorite.mockdata.*
import com.techmesystem.intera.util.buildModels
import kotlinx.android.synthetic.main.view_list_with_toolbar.*

class FavoriteFragment : BaseFragment() {

  override val TAG: String = FavoriteFragment::class.java.name

  private val list = mutableListOf(
    FAVORITE_PRODUCT_1, FAVORITE_PRODUCT_2, FAVORITE_PRODUCT_5, FAVORITE_PRODUCT_6, FAVORITE_PRODUCT_3, FAVORITE_PRODUCT_4, FAVORITE_PRODUCT_7, FAVORITE_PRODUCT_8
  )

  override fun layoutRes(): Int = R.layout.view_list_with_toolbar

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    toolbar.setBackNavigationEnabled(false)
    toolbar.title = getString(R.string.favorites)

    setFavoriteList()
  }

  private fun setFavoriteList() {
    erv_list.buildModels {
      list.forEach { product ->
        productCheckoutItem {
          id(product.toString())
          withProduct(product)
          favoriteAllowed(true)
          editAllowed(false)
          removeAllowed(false)
          isInFavoriteList(true)
          onFavoriteClick {
            list.remove(it)
            setFavoriteList()
          }
        }
      }
    }
  }
}