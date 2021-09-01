package com.soulinc.spokeFolks.base.prefs

import android.content.Context
import android.content.SharedPreferences
import com.soulinc.spokeFolks.auth.data.model.AuthResponse
import javax.inject.Inject

/**
 * paras's creation on 01-05-2021
 */
open class AuthPrefs @Inject constructor(context: Context) {

  private var authPrefs: SharedPreferences =
    context.getSharedPreferences(PREF_AUTH_CREDENTIALS, Context.MODE_PRIVATE)

  companion object {
    const val LOGGED_IN = "loggedIn"
    const val LOGGED_OUT = "loggedOut"
  }

  val authToken: String?
    get() = authPrefs.getString(PREF_AUTH_TOKEN, null)

  val refreshToken: String?
    get() = authPrefs.getString(PREF_REFRESH_TOKEN, null)

  fun isLoggedIn(): Boolean = authPrefs.contains(PREF_AUTH_TOKEN) || !authToken.isNullOrEmpty()

  fun refreshCreds(authResponse: AuthResponse) {
    authPrefs.edit().putString(PREF_AUTH_TOKEN, authResponse.authToken).apply()
  }
}