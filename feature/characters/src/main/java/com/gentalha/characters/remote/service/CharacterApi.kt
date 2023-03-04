package com.gentalha.characters.remote.service

import com.gentalha.characters.remote.model.CharacterResponse
import com.gentalha.config.network.model.ResultResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CharacterApi {

    @GET("people/")
    suspend fun getCharacters(@Query("page") page: Int): ResultResponse<CharacterResponse>

    @GET("people/{id}/")
    suspend fun getCharacterDetail(@Path("id") id: Int): CharacterResponse
}