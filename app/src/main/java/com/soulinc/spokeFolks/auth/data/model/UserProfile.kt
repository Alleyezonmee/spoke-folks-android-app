package com.soulinc.spokeFolks.auth.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.soulinc.spokeFolks.EMPTY_STRING
import kotlinx.parcelize.Parcelize

/**
 * paras's creation on 01-05-2021
 */
@Parcelize
data class UserProfile(
    @SerializedName("name") val name: String = EMPTY_STRING,

    @SerializedName("userName") val userName: String = EMPTY_STRING,

    @SerializedName("bio") val bio: String = EMPTY_STRING,

    @SerializedName("totalDistance") val totalDistance: Int = 0,

    @SerializedName("tripsCount") val tripsCount: Int = 0
) : Parcelable