package com.soulinc.spokeFolks.base.network

import com.soulinc.spokeFolks.AUTH_TOKEN
import com.soulinc.spokeFolks.base.prefs.AuthPrefs
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

/**
 * paras's creation on 12-07-2021
 */
class NetworkInterceptor @Inject constructor(private val authPrefs: AuthPrefs) : Interceptor {
  override fun intercept(chain: Interceptor.Chain): Response {
    val requestBuilder = chain.request().newBuilder()

    val authToken = authPrefs.authToken

    if (!authToken.isNullOrEmpty()) {
      requestBuilder.addHeader(AUTH_TOKEN, authToken)
    }

    return chain.proceed(requestBuilder.method(chain.request().method(), chain.request().body()).build())
  }
}