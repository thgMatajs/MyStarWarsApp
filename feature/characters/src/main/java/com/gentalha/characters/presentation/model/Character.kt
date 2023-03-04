package com.gentalha.characters.presentation.model

import com.gentalha.characters.remote.model.CharacterResponse

data class Character(
    val name: String,
    val url: String,
    val birthYear: String,
    val gender: String
) {
    val id = url.replace("\\D".toRegex(), "").toInt()

    val imgUrl = "https://starwars-visualguide.com/assets/img/characters/$id.jpg"
}

fun CharacterResponse.toUi() = Character(
    url = this.url,
    name = this.name,
    birthYear = this.birthYear,
    gender = this.gender
)