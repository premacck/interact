package com.techmesystem.intera

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import com.techmesystem.intera.base.BaseActivity
import com.techmesystem.intera.checkout.ui.CheckoutFragment
import com.techmesystem.intera.favorite.ui.FavoriteFragment
import com.techmesystem.intera.help.ui.HelpFragment
import com.techmesystem.intera.home.ui.HomeFragment
import com.techmesystem.intera.profile.ui.ProfileFragment
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : BaseActivity() {

  private lateinit var activeFragment: Fragment
  private lateinit var homeFragment: HomeFragment
  private lateinit var favoriteFragment: FavoriteFragment
  private lateinit var helpFragment: HelpFragment
  private lateinit var checkoutFragment: CheckoutFragment
  private lateinit var profileFragment: ProfileFragment

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_home)
    initRootFragments()
    loadAndHideRootFragments()

    activeFragment = homeFragment
    navigateBeforeAttachingChildren(R.id.navigation_profile)

    initListeners()
  }

  private fun initRootFragments() {
    homeFragment = HomeFragment()
    favoriteFragment = FavoriteFragment()
    helpFragment = HelpFragment()
    checkoutFragment = CheckoutFragment()
    profileFragment = ProfileFragment()
  }

  private fun loadAndHideRootFragments() {
    loadAndHideFragment(homeFragment, homeFragment.TAG)
    loadAndHideFragment(favoriteFragment, favoriteFragment.TAG)
    loadAndHideFragment(helpFragment, helpFragment.TAG)
    loadAndHideFragment(checkoutFragment, checkoutFragment.TAG)
    loadAndHideFragment(profileFragment, profileFragment.TAG)
  }

  private fun loadAndHideFragment(fragment: Fragment, tag: String) {
    supportFragmentManager.beginTransaction().add(R.id.fl_root_fragment_container, fragment, tag).hide(fragment).commit()
  }

  private fun initListeners() {
    tl_bottom_navigation.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
      override fun onTabReselected(tab: TabLayout.Tab?) {}
      override fun onTabUnselected(tab: TabLayout.Tab?) {}
      override fun onTabSelected(tab: TabLayout.Tab?) {
        when (tab?.position) {
          0 -> navigateAfterAttachingChildren(R.id.navigation_home)
          1 -> navigateAfterAttachingChildren(R.id.navigation_favorite)
          2 -> navigateAfterAttachingChildren(R.id.navigation_help)
          3 -> navigateAfterAttachingChildren(R.id.navigation_checkout)
          4 -> navigateAfterAttachingChildren(R.id.navigation_profile)
        }
      }
    })
  }

  private fun navigateTo(menuItemId: Int) {
    tl_bottom_navigation.getTabAt(
      when (menuItemId) {
        R.id.navigation_home -> 0
        R.id.navigation_favorite -> 1
        R.id.navigation_help -> 2
        R.id.navigation_checkout -> 3
        R.id.navigation_profile -> 4
        else -> 0
      }
    )?.select()
  }

  /**
   * Safe to call when home, favorite, notification, or account are not yet attached.
   */
  @Suppress("SameParameterValue") private fun navigateBeforeAttachingChildren(@IdRes menuItemId: Int) {
    navigateTo(menuItemId)
    handleNavigation(menuItemId)
  }

  /**
   * Functions use lateinit property or UI element inside children fragment.
   * Unsafe to call when home, favorite, notification, or account are not yet attached.
   */
  private fun navigateAfterAttachingChildren(menuItemId: Int): Boolean = handleNavigation(menuItemId)

  private fun handleNavigation(menuItemId: Int): Boolean = when (menuItemId) {
    R.id.navigation_home -> {
      overrideCurrentFragmentWith(homeFragment)
      activeFragment = homeFragment
      true
    }
    R.id.navigation_favorite -> {
      overrideCurrentFragmentWith(favoriteFragment)
      activeFragment = favoriteFragment
      true
    }
    R.id.navigation_help -> {
      overrideCurrentFragmentWith(helpFragment)
      activeFragment = helpFragment
      true
    }
    R.id.navigation_checkout -> {
      overrideCurrentFragmentWith(checkoutFragment)
      activeFragment = checkoutFragment
      true
    }
    R.id.navigation_profile -> {
      overrideCurrentFragmentWith(profileFragment)
      activeFragment = profileFragment
      true
    }
    else -> false
  }

  private fun overrideCurrentFragmentWith(fragment: Fragment) {
    if (::activeFragment.isInitialized) {
      supportFragmentManager.beginTransaction().apply {
        hide(activeFragment)
        show(fragment)
        commitAllowingStateLoss()
      }
    }
  }
}
