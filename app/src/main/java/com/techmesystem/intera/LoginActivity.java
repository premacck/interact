package com.techmesystem.intera;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        setContentView(R.layout.activity_login);

        Button loginBtn = findViewById(R.id.loginBtn_ids);
        ImageView backMe = findViewById(R.id.backMe_ids);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                finish();
                overridePendingTransition(R.anim.enter_anim, R.anim.exit_anim);
            }
        });

        backMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, BiometricActivity.class));
                finish();
                overridePendingTransition(R.anim.enter_anim, R.anim.exit_anim);
            }
        });
    }
}
