package com.soulinc.spokeFolks.account.ui

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.soulinc.spokeFolks.R
import com.soulinc.spokeFolks.REQUEST_LOGIN
import com.soulinc.spokeFolks.auth.data.model.User
import com.soulinc.spokeFolks.auth.ui.AuthActivity
import com.soulinc.spokeFolks.auth.viewmodel.AuthViewModel
import com.soulinc.spokeFolks.base.lifecycle.BaseFragment
import com.soulinc.spokeFolks.base.prefs.AuthPrefs
import com.soulinc.spokeFolks.databinding.FragmentAccountBinding
import com.soulinc.spokeFolks.hideView
import com.soulinc.spokeFolks.showView
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * paras's creation on 13-07-2021
 */
@AndroidEntryPoint
class AccountFragment : BaseFragment(R.layout.fragment_account) {

  private lateinit var binding: FragmentAccountBinding

  @Inject
  lateinit var authPrefs: AuthPrefs

  private val authViewModel by viewModels<AuthViewModel>()

  companion object {
    fun newInstance() = AccountFragment()
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
    binding = FragmentAccountBinding.inflate(inflater, container, false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    observeUserData()
    updateUi()
    initListeners()
  }

  private fun observeUserData() {
    authViewModel.userData.observe(viewLifecycleOwner, { response ->
      if (response.isSuccessful) {
        response.body()?.result?.let {
          showUserInfo(it)
        }
      }
    })
  }

  private fun initListeners() {
    binding.tvUserName.setOnClickListener {
      if (!authPrefs.isLoggedIn()) {
        AuthActivity.launch(requireActivity(), REQUEST_LOGIN)
      }
    }

    binding.tvLogout.setOnClickListener {
      authPrefs.doLogout()
      updateUi()
    }
  }

  private fun updateUi() {
    if (authPrefs.isLoggedIn()) showLoggedInUi() else showLoggedOutUi()
  }

  private fun showLoggedInUi() {
    binding.groupUserShowcase.showView()
    authViewModel.getUserDetails()
  }

  private fun showLoggedOutUi() {
    binding.groupUserShowcase.hideView()
    binding.tvUserName.text = getString(R.string.login_signup)
  }

  private fun showUserInfo(user: User) {
    binding.tvUserName.text = user.userProfile.name
  }

  override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    if (resultCode == RESULT_OK) {
      when (requestCode) {
        REQUEST_LOGIN -> {
          Toast.makeText(requireContext(), "Authentication Successful", Toast.LENGTH_SHORT).show()
          updateUi()
        }
      }
    }
  }
}