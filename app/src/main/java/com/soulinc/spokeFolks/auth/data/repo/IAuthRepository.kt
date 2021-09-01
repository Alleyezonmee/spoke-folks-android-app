package com.soulinc.spokeFolks.auth.data.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.soulinc.spokeFolks.auth.data.model.AuthRequestBody
import com.soulinc.spokeFolks.auth.data.model.AuthResponse
import com.soulinc.spokeFolks.auth.data.model.CheckUserNameRequestBody
import com.soulinc.spokeFolks.auth.data.model.User
import com.soulinc.spokeFolks.base.data.model.BaseResponse
import com.soulinc.spokeFolks.base.data.repository.BaseRepository
import retrofit2.Response

/**
 * paras's creation on 01-05-2021
 */
interface IAuthRepository: BaseRepository {
    suspend fun registerUser(liveData: MutableLiveData<Response<BaseResponse<AuthResponse>>>, authRequestBody: AuthRequestBody) : LiveData<Response<BaseResponse<AuthResponse>>>

    suspend fun login(liveData: MutableLiveData<Response<BaseResponse<AuthResponse>>>, authRequestBody: AuthRequestBody) : LiveData<Response<BaseResponse<AuthResponse>>>

    suspend fun checkUserName(checkUserNameRequestBody: CheckUserNameRequestBody): LiveData<Response<BaseResponse<String>>>

    suspend fun getUserDetails(liveData: MutableLiveData<Response<BaseResponse<User>>>): LiveData<Response<BaseResponse<User>>>
}