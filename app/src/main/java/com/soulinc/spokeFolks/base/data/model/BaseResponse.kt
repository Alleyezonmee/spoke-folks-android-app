package com.soulinc.spokeFolks.base.data.model

import com.google.gson.annotations.SerializedName
import com.soulinc.spokeFolks.EMPTY_STRING

/**
 * paras's creation on 01-05-2021
 */
data class BaseResponse<T>(
    @SerializedName("code") val code: Int = 200,

    @SerializedName("message") val message: String = EMPTY_STRING,

    @SerializedName("status") val status: Boolean = true,

    @SerializedName("result") val result: T? = null,

    @SerializedName("error") val error: Any? = null
)