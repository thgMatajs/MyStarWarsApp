package com.gentalha.characters.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.gentalha.characters.remote.model.CharacterResponse
import com.gentalha.characters.remote.service.CharacterApi

const val ONE = 1

class CharacterPagingSource(
    private val api: CharacterApi
) : PagingSource<Int, CharacterResponse>() {

    override fun getRefreshKey(state: PagingState<Int, CharacterResponse>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(ONE)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(ONE)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterResponse> {
        return try {
            val page = params.key ?: ONE
            val response = api.getCharacters(page)
            LoadResult.Page(
                data = response.results,
                prevKey = if (page == ONE) null else page.minus(ONE),
                nextKey = if (response.results.isEmpty()) null else page.plus(ONE)
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}