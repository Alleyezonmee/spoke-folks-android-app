package com.soulinc.spokeFolks.auth.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.soulinc.spokeFolks.EMPTY_STRING
import kotlinx.parcelize.Parcelize

/**
 * paras's creation on 01-05-2021
 */
@Parcelize
data class User(
    @SerializedName("email") val email: String = EMPTY_STRING,

    @SerializedName("profile") val userProfile: UserProfile = UserProfile()
) : Parcelable {
}