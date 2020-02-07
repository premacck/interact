package com.techmesystem.intera;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.WindowManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.techmesystem.intera.Fragment.FavoriteFragment;
import com.techmesystem.intera.Fragment.HomeFragment;
import com.techmesystem.intera.Fragment.ProfileFragment;
import com.techmesystem.intera.Fragment.SocialFragment;
import com.techmesystem.intera.Fragment.SupportFragment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class HomeActivity extends AppCompatActivity implements NavigationMe {
    private BottomNavigationView bottomNavView;
    private Fragment mFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        setContentView(R.layout.activity_main);

        bottomNavView = findViewById(R.id.bottomNavView_ids);

        mFragment = new HomeFragment();
        navigateMe(mFragment, false);

    }

    @Override
    protected void onStart() {
        super.onStart();

        bottomNavView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.intera_ids:
                        mFragment = new HomeFragment();
                        navigateMe(mFragment, false);
                        return true;

                    case R.id.favourite_ids:
                        mFragment = new FavoriteFragment();
                        navigateMe(mFragment, false);
                        return true;

                    case R.id.support_ids:
                        mFragment = new SupportFragment();
                        navigateMe(mFragment, false);
                        return true;

                    case R.id.social_ids:
                        mFragment = new SocialFragment();
                        navigateMe(mFragment, false);
                        return true;

                    case R.id.profile_ids:
                        mFragment = new ProfileFragment();
                        navigateMe(mFragment, false);
                        return true;
                }
                return false;
            }
        });
    }

    @Override
    public void navigateMe(Fragment fragment, boolean addToBackStack) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.enter_anim, R.anim.exit_anim, R.anim.pop_up_enter_anim, R.anim.pop_up_exit_anim);
        transaction.replace(R.id.homeFrame_ids, fragment);
        if (addToBackStack)
            transaction.addToBackStack(null);
        transaction.commit();
    }
}
