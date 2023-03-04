package com.gentalha.mystarwarsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.gentalha.characters.presentation.CharacterDetailViewModel
import com.gentalha.characters.presentation.CharacterViewModel
import com.gentalha.characters.presentation.ui.CharacterDetailScreen
import com.gentalha.characters.presentation.ui.CharacterNav
import com.gentalha.characters.presentation.ui.CharactersScreen
import com.gentalha.theme.ui.theme.MyStarWarsAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@ExperimentalMaterial3Api
class MainActivity : ComponentActivity() {
    private val viewModel: CharacterViewModel by viewModels()
    private val detailViewModel: CharacterDetailViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyStarWarsAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = CharacterNav.List.route
                    ) {
                        composable(CharacterNav.List.route) {
                            CharactersScreen(viewModel) {
                                navController.navigate(CharacterNav.Detail.createRoute(it))
                            }
                        }
                        composable(
                            route = CharacterNav.Detail.route,
                            arguments = listOf(navArgument(CharacterNav.Detail.key) {
                                type = NavType.IntType
                            })
                        ) { navBackStackEntry ->
                            val id =
                                navBackStackEntry.arguments?.getInt(CharacterNav.Detail.key) ?: 0
                            CharacterDetailScreen(detailViewModel, id)
                        }
                    }

                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyStarWarsAppTheme {
    }
}