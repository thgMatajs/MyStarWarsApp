package com.gentalha.characters.presentation

import androidx.lifecycle.ViewModel
import com.gentalha.characters.data.CharacterRepository
import com.gentalha.characters.presentation.model.Character
import com.gentalha.characters.presentation.model.toUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    private val repository: CharacterRepository
) : ViewModel() {
    fun getCharacterDetail(id: Int): Flow<Result<Character>> = flow {
        println("THG_LOG --> getCharacterDetail")
        try {
            emit(Result.success(repository.getCharacterDetail(id).toUi()))
        } catch (e: Exception) {
            emit(Result.failure(e))
        }


    }
}