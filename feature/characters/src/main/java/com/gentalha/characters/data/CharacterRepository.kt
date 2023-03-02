package com.gentalha.characters.data

import com.gentalha.characters.remote.model.CharacterResponse
import com.gentalha.characters.remote.service.CharacterApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CharacterRepository(
    private val api: CharacterApi
) {

    fun getCharacters(): Flow<List<CharacterResponse>> = flow {
        emit(api.getCharacters().results)
    }

    fun getCharacterDetail(url: String): Flow<CharacterResponse> = flow {
        emit(api.getCharacterDetail(url))
    }
}