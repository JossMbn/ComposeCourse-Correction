package com.formation.composeformation.ui.day_1.home.home.presentation

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.formation.composeformation.designsystem.component.HeaderView
import com.formation.composeformation.designsystem.component.LoadingView
import com.formation.composeformation.ui.day_1.home.home.composable.HomeSectionRowView
import com.formation.composeformation.ui.day_1.home.home.navigation.HomePageNavigator

@Composable
fun HomePageRoot(
    viewModel: HomeViewModel = hiltViewModel(),
    navigator: HomePageNavigator
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    AnimatedContent(
        modifier = Modifier.background(MaterialTheme.colorScheme.background),
        targetState = state.isLoading
    ) { isLoading ->
        if (isLoading) {
            LoadingView(modifier = Modifier.fillMaxSize())
        } else {
            HomePage(state = state, navigator = navigator)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomePage(
    state: HomeState,
    navigator: HomePageNavigator
) {
    Scaffold(
        topBar = {
            HeaderView(
                title = "Home",
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    titleContentColor = MaterialTheme.colorScheme.onBackground
                )
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(
                top = innerPadding.calculateTopPadding() + 16.dp,
                bottom = innerPadding.calculateBottomPadding() + 16.dp
            )
        ) {
            item(key = 0) {
                HomeSectionRowView(sectionTitle = "Popular") {
                    items(state.popularMovies, key = { it.id }) { movie ->
                        AsyncImage(
                            modifier = Modifier
                                .fillMaxWidth()
                                .heightIn(min = 220.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .clickable { navigator.navigateToDetail(movie.id) },
                            model = movie.posterPath,
                            contentDescription = movie.title,
                            contentScale = ContentScale.FillHeight
                        )

                    }
                }
            }

            item(key = 1) {
                HomeSectionRowView(sectionTitle = "Top Rated") {
                    items(state.topRatedMovies, key = { it.id }) { movie ->
                        AsyncImage(
                            modifier = Modifier
                                .fillMaxWidth()
                                .heightIn(min = 220.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .clickable { navigator.navigateToDetail(movie.id) },
                            model = movie.posterPath,
                            contentDescription = movie.title,
                            contentScale = ContentScale.FillHeight
                        )

                    }
                }
            }

            item(key = 2) {
                HomeSectionRowView(sectionTitle = "Top Rated") {
                    items(state.topRatedMovies, key = { it.id }) { movie ->
                        AsyncImage(
                            modifier = Modifier
                                .fillMaxWidth()
                                .heightIn(min = 220.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .clickable { navigator.navigateToDetail(movie.id) },
                            model = movie.posterPath,
                            contentDescription = movie.title,
                            contentScale = ContentScale.FillHeight
                        )

                    }
                }
            }

            item(key = 3) {
                HomeSectionRowView(sectionTitle = "Top Rated") {
                    items(state.topRatedMovies, key = { it.id }) { movie ->
                        AsyncImage(
                            modifier = Modifier
                                .fillMaxWidth()
                                .heightIn(min = 220.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .clickable { navigator.navigateToDetail(movie.id) },
                            model = movie.posterPath,
                            contentDescription = movie.title,
                            contentScale = ContentScale.FillHeight
                        )

                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun HomePagePreview() {
    HomePage(
        state = HomeState(),
        navigator = HomePageNavigator()
    )
}
