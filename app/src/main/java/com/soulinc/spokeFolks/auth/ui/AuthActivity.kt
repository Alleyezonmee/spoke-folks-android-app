package com.soulinc.spokeFolks.auth.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.soulinc.spokeFolks.R
import com.soulinc.spokeFolks.account.ui.AccountFragment
import com.soulinc.spokeFolks.base.lifecycle.BaseActivity
import com.soulinc.spokeFolks.databinding.ActivityContainerBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * paras's creation on 01-05-2021
 */
@AndroidEntryPoint
class AuthActivity : BaseActivity(R.layout.activity_container) {

  private lateinit var binding: ActivityContainerBinding

  override fun fragmentContainer(): Int = R.id.fl_container

  companion object {
    fun launch(from: Activity, requestCode: Int) = from.run { startActivityForResult(Intent(this, AuthActivity::class.java), requestCode) }

    fun launchForAccount(from: Activity) = from.run { startActivity(Intent(this, AuthActivity::class.java).apply { putExtra("start_account", true) }) }
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityContainerBinding.inflate(layoutInflater)
    setContentView(binding.root)
    if (savedInstanceState == null) {
      val startAccount = intent.extras?.getBoolean("start_account") ?: false
      if (startAccount) {
        showFragment<AccountFragment>(AccountFragment(), false)
      } else {
        showFragment<AuthFragment>(AuthFragment(), false)
      }
    }
  }
}