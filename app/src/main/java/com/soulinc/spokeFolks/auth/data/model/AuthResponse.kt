package com.soulinc.spokeFolks.auth.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.soulinc.spokeFolks.EMPTY_STRING
import kotlinx.parcelize.Parcelize

/**
 * paras's creation on 10-07-2021
 */
@Parcelize
data class AuthResponse(
  @SerializedName("userId") val userId: String = EMPTY_STRING,

  @SerializedName("auth-token") val authToken: String = EMPTY_STRING,

  @SerializedName("email") val email: String = EMPTY_STRING
) : Parcelable