package com.soulinc.spokeFolks.auth.ui

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.soulinc.spokeFolks.R
import com.soulinc.spokeFolks.REQUEST_LOGIN
import com.soulinc.spokeFolks.auth.data.model.AuthRequestBody
import com.soulinc.spokeFolks.auth.viewmodel.AuthViewModel
import com.soulinc.spokeFolks.base.lifecycle.BaseFragment
import com.soulinc.spokeFolks.base.prefs.AuthPrefs
import com.soulinc.spokeFolks.databinding.FragmentAuthBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * paras's creation on 01-05-2021
 */
@AndroidEntryPoint
class AuthFragment : BaseFragment(R.layout.fragment_auth) {

  private lateinit var binding: FragmentAuthBinding

  @Inject
  lateinit var authPrefs: AuthPrefs
  private val authRequestBody = AuthRequestBody()
  private val authViewModel by viewModels<AuthViewModel>()
  private var forLogin: Boolean = true

  companion object {
    const val USER = "user"
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
    binding = FragmentAuthBinding.inflate(inflater, container, false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    initListeners()
    observeUserData()
    updateUi()
  }

  private fun initListeners() {
    binding.btnSubmit.setOnClickListener {
      updateRequestBody()
      if (isFormComplete()) {
        if (forLogin) authViewModel.login(authRequestBody) else authViewModel.register(authRequestBody)
      } else {
        validateForm()
      }
    }
  }

  private fun updateUi() {
    if (forLogin) {
      binding.tvHeading.text = getString(R.string.login)
      binding.btnSubmit.text = getString(R.string.login)
      binding.tvOtherAction.text = getString(R.string.new_user_signup)
      binding.tvOtherAction.setOnClickListener {
        forLogin = false
        updateUi()
      }
    } else {
      binding.tvHeading.text = getString(R.string.register)
      binding.btnSubmit.text = getString(R.string.register)
      binding.tvOtherAction.text = getString(R.string.registered_user_login)
      binding.tvOtherAction.setOnClickListener {
        forLogin = true
        updateUi()
      }
    }
  }

  private fun observeUserData() {
    authViewModel.authResponseData.observe(viewLifecycleOwner, Observer { response ->
      if (response.isSuccessful) {
        response.body()?.result?.let {
          authPrefs.refreshCreds(it)
          activity?.setResult(RESULT_OK, Intent().apply { putExtra(USER, it) })
          activity?.finish()
        }
      }
    })
  }

  private fun isFormComplete(): Boolean {
    var result = true
    if (binding.etEmail.text.toString().isBlank() || authRequestBody.email.isBlank()) result = false
    if (binding.etPassword.text.toString().isBlank() || authRequestBody.password.isBlank()) result = false
    return result
  }

  private fun updateRequestBody() {
    authRequestBody.run {
      email = binding.etEmail.text.toString()
      password = binding.etPassword.text.toString()
    }
  }

  private fun validateForm() {
    Toast.makeText(requireContext(), "Please fill the details", Toast.LENGTH_SHORT).show()
  }
}