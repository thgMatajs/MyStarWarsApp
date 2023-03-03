package com.gentalha.characters.remote.model

import com.google.gson.annotations.SerializedName

data class CharacterResponse(
    @SerializedName("name") val name: String,
    val url: String,
    @SerializedName("birth_year") val birthYear: String,
    @SerializedName("gender") val gender: String,
)