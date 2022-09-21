package com.galih.suitmediatest.base

import com.galih.suitmediatest.data.responses.ModelConstants
import com.google.gson.annotations.SerializedName

data class BaseListResponse<T>(

    @SerializedName(ModelConstants.DATA)
    val results: List<T>,

    @SerializedName(ModelConstants.PAGE)
    val page: Int? = null,

    @SerializedName(ModelConstants.TOTAL_PAGES)
    val totalPages: Int? = null
)