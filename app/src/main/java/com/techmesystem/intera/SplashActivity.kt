package com.techmesystem.intera

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.view.View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
import android.view.View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
import android.view.WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.postDelayed

class SplashActivity : AppCompatActivity() {

  private val handler = Handler()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    window?.setFlags(FLAG_LAYOUT_NO_LIMITS, FLAG_LAYOUT_NO_LIMITS)
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
      window?.decorView?.systemUiVisibility = SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        window?.decorView?.systemUiVisibility = SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
      }
    }
    handler.postDelayed(1000) {
      startActivity(Intent(this@SplashActivity, BiometricActivity::class.java))
      finish()
      overridePendingTransition(R.anim.enter_anim, R.anim.exit_anim)
    }
  }

  override fun onDestroy() {
    handler.removeCallbacksAndMessages(null)
    super.onDestroy()
  }
}
