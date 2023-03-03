package com.gentalha.characters.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.gentalha.characters.remote.model.CharacterResponse
import com.gentalha.characters.remote.service.CharacterApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

private const val DEFAULT_PAGE_SIZE = 20

class CharacterRepository @Inject constructor(
    private val api: CharacterApi
) {

    fun getCharacters() = Pager(
        config = PagingConfig(pageSize = DEFAULT_PAGE_SIZE),
        pagingSourceFactory = { CharacterPagingSource(api) }
    ).flow

    fun getCharacterDetail(url: String): Flow<CharacterResponse> = flow {
        emit(api.getCharacterDetail(url))
    }
}