package com.techmesystem.intera.help.ui

import android.content.Context
import android.os.Bundle
import com.techmesystem.intera.R
import com.techmesystem.intera.base.BaseActivity
import org.jetbrains.anko.startActivity

/**
 * Prem's creation, on 2020-02-09
 */
class HelpActivity : BaseActivity() {

  companion object {
    fun launch(from: Context?) = from?.run { startActivity<HelpActivity>() }
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_fragment_container)
    if (savedInstanceState == null) {
      val fragment = HelpFragment.newInstance(true)
      supportFragmentManager.beginTransaction().replace(R.id.fl_root_fragment_container, fragment, fragment.TAG).commit()
    }
  }
}