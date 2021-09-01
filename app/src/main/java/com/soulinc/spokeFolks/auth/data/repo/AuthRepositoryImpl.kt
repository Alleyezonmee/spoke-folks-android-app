package com.soulinc.spokeFolks.auth.data.repo

import androidx.lifecycle.MutableLiveData
import com.soulinc.spokeFolks.auth.data.model.AuthRequestBody
import com.soulinc.spokeFolks.auth.data.model.AuthResponse
import com.soulinc.spokeFolks.auth.data.model.CheckUserNameRequestBody
import com.soulinc.spokeFolks.auth.data.model.User
import com.soulinc.spokeFolks.auth.data.network.IAuthService
import com.soulinc.spokeFolks.base.data.model.BaseResponse
import com.soulinc.spokeFolks.base.data.repository.BaseRepositoryImpl
import retrofit2.Response
import javax.inject.Inject

/**
 * paras's creation on 01-05-2021
 */
class AuthRepositoryImpl @Inject constructor(private val authService: IAuthService) :
  BaseRepositoryImpl(), IAuthRepository {
  override suspend fun registerUser(
    liveData: MutableLiveData<Response<BaseResponse<AuthResponse>>>,
    authRequestBody: AuthRequestBody
  ) = callApi(liveData) { authService.registerUser(authRequestBody) }

  override suspend fun login(
    liveData: MutableLiveData<Response<BaseResponse<AuthResponse>>>,
    authRequestBody: AuthRequestBody
  ) = callApi(liveData) { authService.login(authRequestBody) }

  override suspend fun checkUserName(checkUserNameRequestBody: CheckUserNameRequestBody) = callApi { authService.checkUserName(checkUserNameRequestBody) }

  override suspend fun getUserDetails(liveData: MutableLiveData<Response<BaseResponse<User>>>) = callApi(liveData) { authService.getUserDetails() }
}