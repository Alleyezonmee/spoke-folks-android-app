package com.soulinc.spokeFolks.auth.data.network

import com.soulinc.spokeFolks.auth.data.model.AuthRequestBody
import com.soulinc.spokeFolks.auth.data.model.AuthResponse
import com.soulinc.spokeFolks.auth.data.model.CheckUserNameRequestBody
import com.soulinc.spokeFolks.auth.data.model.User
import com.soulinc.spokeFolks.base.data.model.BaseResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT

/**
 * paras's creation on 01-05-2021
 */
interface IAuthService {
    @POST("user/register") suspend fun registerUser(@Body authRequestBody: AuthRequestBody) : Response<BaseResponse<AuthResponse>>

    @POST("user/login") suspend fun login(@Body authRequestBody: AuthRequestBody) : Response<BaseResponse<AuthResponse>>

    @POST("user/checkUserName") suspend fun checkUserName(@Body checkUserNameRequestBody: CheckUserNameRequestBody) : Response<BaseResponse<String>>

    @GET("user/") suspend fun getUserDetails() : Response<BaseResponse<User>>
}