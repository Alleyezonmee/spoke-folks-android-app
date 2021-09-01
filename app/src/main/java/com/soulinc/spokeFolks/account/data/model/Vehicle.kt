package com.soulinc.spokeFolks.account.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.soulinc.spokeFolks.EMPTY_STRING
import kotlinx.parcelize.Parcelize

@Parcelize
data class Vehicle(
  @SerializedName("userId") val userId: String = EMPTY_STRING,

  @SerializedName("name") val name: String = EMPTY_STRING,

  @SerializedName("brand") val brand: String = EMPTY_STRING,

  @SerializedName("model") val model: String = EMPTY_STRING,

  @SerializedName("year") val year: Int = 0,

  @SerializedName("distanceCovered") val distanceCovered: Int = 0
) : Parcelable