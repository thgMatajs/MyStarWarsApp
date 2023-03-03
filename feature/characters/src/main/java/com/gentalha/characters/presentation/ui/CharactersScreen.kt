package com.gentalha.characters.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import coil.compose.AsyncImage
import com.gentalha.characters.presentation.CharacterViewModel
import com.gentalha.characters.presentation.model.Character

@ExperimentalMaterial3Api
@Composable
fun CharactersScreen(viewModel: CharacterViewModel) {
    val characters = viewModel.getCharacters().collectAsLazyPagingItems()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when (val state = characters.loadState.prepend) {
            LoadState.Loading -> Loading()
            is LoadState.NotLoading -> Unit
            is LoadState.Error ->  ShowError(state.error.message ?: "")

        }
        when (val state = characters.loadState.refresh) {
            is LoadState.Error -> ShowError(state.error.message ?: "")
            LoadState.Loading -> Loading()
            is LoadState.NotLoading -> Unit
        }
        when (val state = characters.loadState.append) {
            is LoadState.Error -> ShowError(state.error.message ?: "")
            LoadState.Loading -> Loading()
            is LoadState.NotLoading -> Unit
        }
        items(items = characters) {
            it?.let {
                CharacterItem(character = it)
            }
        }

    }
}

@ExperimentalMaterial3Api
@Composable
fun CharacterItem(
    character: Character
) {
    Spacer(modifier = Modifier.height(8.dp))
    ElevatedCard(
        shape = RoundedCornerShape(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = character.imgUrl,
                contentDescription = character.name,
                modifier = Modifier.fillMaxHeight().padding(end = 8.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = character.name,
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 4.dp)
                )
                Text(
                    text = character.gender,
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 4.dp)
                )
                Text(
                    text = character.birthYear,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 4.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

fun LazyListScope.Loading() {
    item {
        CircularProgressIndicator(modifier = Modifier.padding(16.dp))
    }
}

fun LazyListScope.ShowError(message: String) {
    item {
        Text(
            text = message,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.error
        )
    }
}