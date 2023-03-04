package com.gentalha.characters.presentation.ui

sealed class CharacterNav(val route: String) {

    object List : CharacterNav("Characters")

    object Detail : CharacterNav("CharacterDetail/{id}") {
        fun createRoute(id: Int) = "CharacterDetail/$id"

        const val key = "id"
    }
}
