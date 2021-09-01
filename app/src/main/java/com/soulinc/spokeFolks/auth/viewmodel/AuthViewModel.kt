package com.soulinc.spokeFolks.auth.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.soulinc.spokeFolks.auth.data.model.AuthRequestBody
import com.soulinc.spokeFolks.auth.data.model.AuthResponse
import com.soulinc.spokeFolks.auth.data.model.CheckUserNameRequestBody
import com.soulinc.spokeFolks.auth.data.model.User
import com.soulinc.spokeFolks.auth.data.repo.IAuthRepository
import com.soulinc.spokeFolks.base.BaseViewModel
import com.soulinc.spokeFolks.base.data.model.BaseResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

/**
 * paras's creation on 01-05-2021
 */
@HiltViewModel
class AuthViewModel @Inject constructor(private val repo: IAuthRepository) : BaseViewModel() {
  val userData = MutableLiveData<Response<BaseResponse<User>>>()
  val authResponseData = MutableLiveData<Response<BaseResponse<AuthResponse>>>()

  fun register(authRequestBody: AuthRequestBody) = viewModelScope.launch {
    repo.registerUser(authResponseData, authRequestBody)
  }

  fun login(authRequestBody: AuthRequestBody) =
    viewModelScope.launch { repo.login(authResponseData, authRequestBody) }

  fun checkUserName(checkUserNameRequestBody: CheckUserNameRequestBody) =
    viewModelScope.launch { repo.checkUserName(checkUserNameRequestBody) }

  fun getUserDetails() = viewModelScope.launch { repo.getUserDetails(userData) }
}