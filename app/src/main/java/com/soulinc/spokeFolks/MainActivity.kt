package com.soulinc.spokeFolks

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.soulinc.spokeFolks.auth.data.model.AuthResponse
import com.soulinc.spokeFolks.auth.ui.AuthActivity
import com.soulinc.spokeFolks.auth.ui.AuthFragment.Companion.USER
import com.soulinc.spokeFolks.auth.viewmodel.AuthViewModel
import com.soulinc.spokeFolks.base.lifecycle.BaseActivity
import com.soulinc.spokeFolks.base.prefs.AuthPrefs
import com.soulinc.spokeFolks.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : BaseActivity(R.layout.activity_main) {

  private lateinit var binding: ActivityMainBinding

  private val authViewModel by viewModels<AuthViewModel>()

  @Inject
  lateinit var authPrefs: AuthPrefs

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)
  }

  override fun onStart() {
    super.onStart()
    observeUserData()
    initListeners()
    initView()
  }

  private fun observeUserData() {
    authViewModel.userData.observe(this, Observer { response ->
      if (response.isSuccessful) {
        response.body()?.result?.let {
          binding.tvUserDetails.text = it.toString()
        }
      }
    })
  }

  private fun initView() {
    if (authPrefs.isLoggedIn()) {
      binding.btnLogin.hideView()
      binding.btnRegister.hideView()
      binding.tvUserDetails.showView()
      binding.btnAccount.showView()
      authViewModel.getUserDetails()
    } else {
      binding.btnLogin.showView()
      binding.btnRegister.showView()
      binding.tvUserDetails.hideView()
      binding.btnAccount.hideView()
    }
  }

  private fun showUserDetails(authResponse: AuthResponse) {
    binding.tvUserDetails.text = authResponse.toString()
  }

  private fun initListeners() {
    binding.btnLogin.setOnClickListener {
      AuthActivity.launch(this, REQUEST_LOGIN)
    }

    binding.btnRegister.setOnClickListener {
      AuthActivity.launch(this, REQUEST_REGISTER)
    }
    binding.btnAccount.setOnClickListener {
      AuthActivity.launchForAccount(this)
    }
  }

  override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    super.onActivityResult(requestCode, resultCode, data)
    if (resultCode == RESULT_OK) {
      if (requestCode == REQUEST_LOGIN || requestCode == REQUEST_REGISTER) {
        initView()
        data?.getParcelableExtra<AuthResponse>(USER)?.let { showUserDetails(it) }
      }
    }
  }
}