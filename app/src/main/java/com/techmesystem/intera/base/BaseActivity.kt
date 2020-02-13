package com.techmesystem.intera.base

import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.disposables.CompositeDisposable

/**
 * Prem's creation, on 2020-02-05
 */
abstract class BaseActivity : AppCompatActivity() {

  protected val disposable = CompositeDisposable()
  protected val handler: Handler by lazy { Handler() }

  override fun onDestroy() {
    disposable.dispose()
    handler.removeCallbacksAndMessages(null)
    super.onDestroy()
  }
}