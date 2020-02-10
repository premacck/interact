package com.techmesystem.intera.home.ui

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.techmesystem.intera.R
import com.techmesystem.intera.base.BaseFragment
import com.techmesystem.intera.home.adapter.*
import com.techmesystem.intera.home.mockdata.categoryALL
import com.techmesystem.intera.home.mockdata.servicesALL
import kotlinx.android.synthetic.main.fragment_home.*
import java.util.*

class HomeFragment : BaseFragment() {

  override val TAG: String = HomeFragment::class.java.name

  override fun layoutRes(): Int = R.layout.fragment_home

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)

    val homeDataModelsAll = categoryALL

    categoryRecycler_ids?.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
    categoryRecycler_ids?.hasFixedSize()
    categoryRecycler_ids?.isNestedScrollingEnabled = false

    val homeCategoryAdapter = HomeCategoryAdapter(context, homeDataModelsAll)
    categoryRecycler_ids?.adapter = homeCategoryAdapter

    val arrayListP = ArrayList<String>()
    productRecycler_ids?.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
    productRecycler_ids?.hasFixedSize()
    productRecycler_ids?.isNestedScrollingEnabled = false

    val homeProductAdapter = HomeProductAdapter(context, arrayListP)
    productRecycler_ids?.adapter = homeProductAdapter

    val arrayListP2 = ArrayList<String>()
    productTwoRecycler_ids?.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
    productTwoRecycler_ids?.hasFixedSize()
    productTwoRecycler_ids?.isNestedScrollingEnabled = false

    val homeProductTwoAdapter = context?.let { HomeProductTwoAdapter(it, arrayListP2) }
    productTwoRecycler_ids?.adapter = homeProductTwoAdapter

    val arrayListS = servicesALL
    homeCervicesRecycler_ids?.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
    homeCervicesRecycler_ids?.hasFixedSize()
    homeCervicesRecycler_ids?.isNestedScrollingEnabled = false

    val homeServicesAdapter = HomeServicesAdapter(context, arrayListS)
    homeCervicesRecycler_ids?.adapter = homeServicesAdapter

    val arrayListC = ArrayList<String>()
    casmaticRecycler_ids?.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
    casmaticRecycler_ids?.hasFixedSize()
    casmaticRecycler_ids?.isNestedScrollingEnabled = false

    val casmaticProductAdapter = context?.let { CasmaticProductAdapter(it, arrayListC) }
    casmaticRecycler_ids?.adapter = casmaticProductAdapter

  }
}