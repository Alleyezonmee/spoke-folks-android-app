package com.soulinc.spokeFolks.account.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.soulinc.spokeFolks.EMPTY_STRING
import kotlinx.parcelize.Parcelize

@Parcelize
data class Achievement(
  @SerializedName("label") val label: String = EMPTY_STRING,

  @SerializedName("code") val code: String = EMPTY_STRING,

  @SerializedName("isEarned") val isEarned: Boolean = false
) : Parcelable