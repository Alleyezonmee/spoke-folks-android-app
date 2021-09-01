package com.soulinc.spokeFolks.base.network

import android.util.Log
import com.soulinc.spokeFolks.AUTH_TOKEN
import com.soulinc.spokeFolks.base.prefs.AuthPrefs
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import javax.inject.Inject

/**
 * paras's creation on 13-07-2021
 */
class NetworkAuthenticator @Inject constructor(private val authPrefs: AuthPrefs) : Authenticator {
  override fun authenticate(route: Route?, response: Response): Request? {
    synchronized(this) {
      val requestUrl = response.request().url().toString()

      if(authPrefs.isLoggedIn()) {
        Log.i("auth", "authenticator called")
        return response.request().newBuilder().header(AUTH_TOKEN, authPrefs.authToken.orEmpty()).build()
      }
    }
    return null
  }
}