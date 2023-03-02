package com.gentalha.characters.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gentalha.characters.data.CharacterRepository
import com.gentalha.characters.presentation.model.toUi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import com.gentalha.characters.presentation.model.Character
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

class CharacterViewModel(
    private val repository: CharacterRepository
) : ViewModel() {

    fun getCharacters(): Flow<List<Character>> {
        return repository.getCharacters()
            .catch { println("THG_ERROR --> $it") }
            .map { charactersResponse ->
            charactersResponse.map { it.toUi() }
        }
    }

    fun getCharacterDetail(url: String) {
        viewModelScope.launch {
            repository.getCharacterDetail(url).collectLatest { character ->
                character.toUi()

            }
        }
    }
}