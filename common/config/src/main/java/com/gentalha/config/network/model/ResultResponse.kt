package com.gentalha.config.network.model

import com.google.gson.annotations.SerializedName

data class ResultResponse<T>(
    @SerializedName("results") val results: List<T>
)