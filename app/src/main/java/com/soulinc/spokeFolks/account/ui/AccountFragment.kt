package com.soulinc.spokeFolks.account.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.soulinc.spokeFolks.R
import com.soulinc.spokeFolks.auth.data.model.User
import com.soulinc.spokeFolks.auth.viewmodel.AuthViewModel
import com.soulinc.spokeFolks.base.lifecycle.BaseFragment
import com.soulinc.spokeFolks.databinding.FragmentAccountBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * paras's creation on 13-07-2021
 */
@AndroidEntryPoint
class AccountFragment : BaseFragment(R.layout.fragment_account) {

  private lateinit var binding: FragmentAccountBinding

  private val authViewModel by viewModels<AuthViewModel>()

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
    binding = FragmentAccountBinding.inflate(inflater, container, false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    observeUserData()
    authViewModel.getUserDetails()
  }

  private fun observeUserData() {
    authViewModel.userData.observe(viewLifecycleOwner, { response ->
      if (response.isSuccessful) {
        response.body()?.result?.let {
          initUi(it)
        }
      }
    })
  }

  private fun initUi(user: User) {
    binding.tvUserName.text = user.userProfile.name
    binding.tvBio.text = user.userProfile.bio
  }
}