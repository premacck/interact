package com.techmesystem.intera

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.biometric_activity.*

class BiometricActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
    setContentView(R.layout.biometric_activity)

    enterEmailBtn_ids?.setOnClickListener {
      startActivity(Intent(this@BiometricActivity, LoginActivity::class.java))
      finish()
      overridePendingTransition(R.anim.enter_anim, R.anim.exit_anim)
    }
  }
}
