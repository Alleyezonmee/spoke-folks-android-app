package com.soulinc.spokeFolks.auth.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.soulinc.spokeFolks.EMPTY_STRING
import kotlinx.parcelize.Parcelize

/**
 * paras's creation on 01-05-2021
 */
@Parcelize
data class AuthRequestBody(
    @SerializedName("email") var email : String = EMPTY_STRING,

    @SerializedName("password") var password: String = EMPTY_STRING
) : Parcelable {
}