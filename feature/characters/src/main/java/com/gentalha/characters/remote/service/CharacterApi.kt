package com.gentalha.characters.remote.service

import com.gentalha.characters.remote.model.CharacterResponse
import com.gentalha.config.network.model.ResultResponse
import retrofit2.http.GET
import retrofit2.http.Url

interface CharacterApi {

    @GET("people/")
    suspend fun getCharacters(): ResultResponse<CharacterResponse>

    @GET()
    suspend fun getCharacterDetail(@Url url: String): CharacterResponse
}