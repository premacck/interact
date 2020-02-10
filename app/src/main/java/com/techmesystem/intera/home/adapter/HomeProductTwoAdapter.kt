package com.techmesystem.intera.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.techmesystem.intera.R
import com.techmesystem.intera.productdetail.ui.ProductDetailActivity
import com.techmesystem.intera.util.onDebounceClick
import java.util.*

class HomeProductTwoAdapter(private val mContext: Context, private val categoryModels_all: ArrayList<String>) : RecyclerView.Adapter<HomeProductTwoAdapter.ViewHolder2>() {
  private var itemInterface: clickItemInterface? = null

  fun itemInterfaceClicked(clicked: clickItemInterface) {
    this.itemInterface = clicked
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder2 {
    val itemView = LayoutInflater.from(parent.context).inflate(R.layout.home_product_two_recycler_design, parent, false)
    return ViewHolder2(itemView)
  }

  override fun onBindViewHolder(holder: ViewHolder2, position: Int) {
    holder.itemView.onDebounceClick {
      ProductDetailActivity.launch(mContext, false, System.currentTimeMillis() % 2 == 0L)
    }
  }

  override fun getItemCount(): Int {
    //return categoryModels_all == null ? 0 : categoryModels_all.size();
    return 11
  }

  interface clickItemInterface {

    fun clickItem(categoryID: String, title: String)
  }

  class ViewHolder2(itemView: View) : RecyclerView.ViewHolder(itemView)
}
