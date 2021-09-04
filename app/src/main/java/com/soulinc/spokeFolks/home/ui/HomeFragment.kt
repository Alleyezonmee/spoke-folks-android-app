package com.soulinc.spokeFolks.home.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.soulinc.spokeFolks.R
import com.soulinc.spokeFolks.base.lifecycle.BaseFragment
import com.soulinc.spokeFolks.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment(R.layout.fragment_home) {

  private lateinit var binding: FragmentHomeBinding

  companion object {
    fun newInstance() = HomeFragment()
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    binding.tvTest.text = "Home Fragment"
  }

}