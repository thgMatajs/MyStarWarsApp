package com.gentalha.characters.presentation.model

import com.gentalha.characters.remote.model.CharacterResponse

data class Character(
    val id: Int,
    val name: String,
    val url: String,
    val imgUrl: String,
    val birthYear: String,
    val gender: String
)

fun CharacterResponse.toUi() = Character(
    id = this.id,
    name = this.name,
    url = this.url,
    imgUrl = this.imgUrl,
    birthYear = this.birthYear,
    gender = this.gender
)