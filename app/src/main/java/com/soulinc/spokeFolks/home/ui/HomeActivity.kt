package com.soulinc.spokeFolks.home.ui

import android.os.Bundle
import com.soulinc.spokeFolks.R
import com.soulinc.spokeFolks.account.ui.AccountFragment
import com.soulinc.spokeFolks.base.lifecycle.BaseActivity
import com.soulinc.spokeFolks.databinding.ActivityHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : BaseActivity(R.layout.activity_home) {

  private lateinit var binding: ActivityHomeBinding

  override fun fragmentContainer(): Int = R.id.fcv_container

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityHomeBinding.inflate(layoutInflater)
    setContentView(binding.root)

    initBottomNavigation()
    showFragment<HomeFragment>(HomeFragment.newInstance(), false)
  }

  private fun initBottomNavigation() {
    binding.bnvRoot.setOnNavigationItemSelectedListener { menuItem ->
      when (menuItem.itemId) {
        R.id.action_home -> {
          showFragment<HomeFragment>(HomeFragment.newInstance(), false)
          true
        }
        R.id.action_dream_rides -> {
          showFragment<DreamRidesFragment>(DreamRidesFragment.newInstance(), false)
          true
        }
        R.id.action_ride -> {
          showFragment<RideFragment>(RideFragment.newInstance(), false)
          true
        }
        R.id.action_account -> {
          showFragment<AccountFragment>(AccountFragment.newInstance(), false)
          true
        }
        else -> false
      }
    }
  }
}