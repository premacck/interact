package com.techmesystem.intera.profile.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.techmesystem.intera.R
import com.techmesystem.intera.base.BaseActivity
import com.techmesystem.intera.util.onDebounceClick
import kotlinx.android.synthetic.main.activity_personal_data.*
import org.jetbrains.anko.backgroundColorResource
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.onPageChangeListener

/**
 * Prem's creation, on 2020-02-09
 */
class PersonalDataActivity : BaseActivity() {

  companion object {
    fun launch(context: Context?) = context?.run { startActivity<PersonalDataActivity>() }
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_personal_data)
    btn_back.onDebounceClick { onBackPressed() }

    initViewPager()
  }

  private fun initViewPager() {
    vp_personal_data.onPageChangeListener {
      onPageSelected {
        cl_root_personal_data.backgroundColorResource = if (it == 0) R.color.white else R.color.color_f9f7ec
      }
    }
    vp_personal_data.adapter = PersonalDataPagerAdapter(supportFragmentManager)
    tl_profile_option.setupWithViewPager(vp_personal_data)
  }

  class PersonalDataPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment = if (position == 0) PersonalDataFragment() else AddressesFragment()

    override fun getCount(): Int = 2

    override fun getPageTitle(position: Int): CharSequence? = if (position == 0) "Dados pessoais" else "Endereços"
  }
}