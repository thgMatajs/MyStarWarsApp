package com.gentalha.characters.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.gentalha.characters.presentation.CharacterDetailViewModel

@ExperimentalMaterial3Api
@Composable
fun CharacterDetailScreen(viewModel: CharacterDetailViewModel, id: Int) {
    val character by viewModel.getCharacterDetail(id).collectAsState(initial = null)
    character?.onSuccess {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .background(MaterialTheme.colorScheme.background)
                .fillMaxHeight()
        ) {
            CharacterItem(character = it, {})
        }
    }?.onFailure {
        println("THG_LOG ->ERROR<- $it")
    } ?: println("THG_LOG ->LET<-")
}