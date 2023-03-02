package com.gentalha.characters.remote.model

import androidx.core.text.isDigitsOnly
import com.google.gson.annotations.SerializedName

data class CharacterResponse(
    @SerializedName("name") val name: String,
    val url: String,
    val id: Int = url.filter { url.isDigitsOnly() }.toInt(),
    val imgUrl: String = "https://starwars-visualguide.com/assets/img/characters/$id.jpg",
    @SerializedName("birth_year") val birthYear: String,
    @SerializedName("gender") val gender: String,
)