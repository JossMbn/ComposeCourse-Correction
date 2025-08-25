package com.formation.composeformation.ui.day_1.search.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.formation.composeformation.ui.day_1.search.component.ImageItemView
import com.formation.composeformation.ui.day_1.search.component.SearchMovieItemView
import com.formation.composeformation.ui.day_1.search.component.SearchSectionRowView
import com.formation.composeformation.ui.day_1.search.navigation.SearchPageNavigator

@Composable
fun SearchPageRoot(
    viewModel: SearchViewModel = hiltViewModel(),
    navigator: SearchPageNavigator
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    SearchPage(
        state = state,
        onAction = viewModel::onAction,
        navigator = navigator
    )
}

@Composable
private fun SearchPage(
    state: SearchState,
    onAction: (SearchAction) -> Unit,
    navigator: SearchPageNavigator
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(top = 10.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        BasicTextField(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth(),
            value = state.query,
            onValueChange = { newText -> onAction(SearchAction.OnQueryChange(newText)) },
            maxLines = 1,
            textStyle = MaterialTheme.typography.bodyMedium.copy(
                color = MaterialTheme.colorScheme.onSurfaceVariant
            ),
            cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
            decorationBox = { innerTextField ->
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(MaterialTheme.colorScheme.surfaceVariant)
                        .padding(vertical = 12.dp, horizontal = 10.dp),
                    contentAlignment = Alignment.CenterStart
                ) {
                    if (state.query.isEmpty()) {
                        Text(
                            text = "Search",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }

                    innerTextField()
                }
            }
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            contentPadding = PaddingValues(vertical = 10.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            state.people.takeIf { it.isNotEmpty() }?.let { peoples ->
                item {
                    SearchSectionRowView(
                        sectionTitle = "People",
                    ) {
                        items(peoples) { person ->
                            ImageItemView(
                                modifier = Modifier
                                    .heightIn(min = 160.dp),
                                posterPath = person.profilePath
                            )
                        }
                    }
                }
            }

            state.tvShows.takeIf { it.isNotEmpty() }?.let { tvShows ->
                item {
                    SearchSectionRowView(
                        sectionTitle = "On Tv",
                    ) {
                        items(tvShows) { show ->
                            ImageItemView(
                                modifier = Modifier
                                    .heightIn(min = 160.dp),
                                posterPath = show.posterPath
                            )
                        }
                    }
                }
            }

            state.movies.takeIf { it.isNotEmpty() }?.let { movies ->
                item {
                    Text(
                        modifier = Modifier.padding(horizontal = 16.dp),
                        text = "Movies",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }

                items(movies) { movie ->
                    SearchMovieItemView(
                        modifier = Modifier
                            .padding(horizontal = 16.dp),
                        title = movie.title,
                        posterPath = movie.posterPath,
                        overview = movie.overview,
                        releaseDate = movie.releaseDate.toString(),
                        onClick = { navigator.navigateToDetail(movie.id) }
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun SearchPagePreview() {
    SearchPage(
        state = SearchState(),
        onAction = {},
        navigator = SearchPageNavigator()
    )
}
