package com.gentalha.characters.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.gentalha.characters.remote.model.CharacterResponse
import com.gentalha.characters.remote.service.CharacterApi
import javax.inject.Inject

private const val DEFAULT_PAGE_SIZE = 10

class CharacterRepository @Inject constructor(
    private val api: CharacterApi
) {

    fun getCharacters() = Pager(
        config = PagingConfig(pageSize = DEFAULT_PAGE_SIZE),
        pagingSourceFactory = { CharacterPagingSource(api) }
    ).flow

    suspend fun getCharacterDetail(id: Int): CharacterResponse = api.getCharacterDetail(id)
}