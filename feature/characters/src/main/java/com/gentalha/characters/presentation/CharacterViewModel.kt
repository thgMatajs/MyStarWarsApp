package com.gentalha.characters.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.gentalha.characters.data.CharacterRepository
import com.gentalha.characters.presentation.model.Character
import com.gentalha.characters.presentation.model.toUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(
    private val repository: CharacterRepository
) : ViewModel() {

    fun getCharacters(): Flow<PagingData<Character>> {
        return repository.getCharacters().map {
            it.map { characterResponse ->
                characterResponse.toUi()
            }
        }.cachedIn(viewModelScope)
    }

    fun getCharacterDetail(url: String) {
        viewModelScope.launch {
            repository.getCharacterDetail(url).collectLatest { character ->
                character.toUi()

            }
        }
    }
}