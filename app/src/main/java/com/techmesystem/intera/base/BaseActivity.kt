package com.techmesystem.intera.base

import android.os.Handler
import androidx.appcompat.app.AppCompatActivity

/**
 * Prem's creation, on 2020-02-05
 */
abstract class BaseActivity : AppCompatActivity() {

  protected val handler: Handler by lazy { Handler() }

  override fun onDestroy() {
    handler.removeCallbacksAndMessages(null)
    super.onDestroy()
  }
}