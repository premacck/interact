package com.techmesystem.intera

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.techmesystem.intera.auth.ui.RegisterActivity
import com.techmesystem.intera.home.ui.HomeActivity
import com.techmesystem.intera.util.onDebounceClick
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
    setContentView(R.layout.activity_login)

    loginBtn_ids.setOnClickListener {
      startActivity(Intent(this@LoginActivity, HomeActivity::class.java))
      finish()
      overridePendingTransition(R.anim.enter_anim, R.anim.exit_anim)
    }

    backMe_ids.setOnClickListener {
      startActivity(Intent(this@LoginActivity, BiometricActivity::class.java))
      finish()
      overridePendingTransition(R.anim.enter_anim, R.anim.exit_anim)
    }

    btn_register.onDebounceClick { RegisterActivity.launch(this) }
  }
}
