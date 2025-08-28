package com.formation.composecourse.ui.search

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.formation.composecourse.designsystem.theme.ComposeCourseTheme
import com.formation.composecourse.domain.movie.model.MovieDomain
import com.formation.composecourse.domain.search.model.SearchDomain
import com.formation.composecourse.ui.component.MainPageLazyColumn
import com.formation.composecourse.ui.search.component.ImageItemView
import com.formation.composecourse.ui.search.component.SearchHeaderView
import com.formation.composecourse.ui.search.component.SearchItemView
import com.formation.composecourse.ui.search.model.SearchAction
import com.formation.composecourse.ui.search.model.SearchState
import com.formation.composecourse.ui.search.navigation.SearchNavigator
import com.formation.composecourse.ui.utils.horizontalNegativePadding

@Composable
fun SearchRoot(
    viewModel: SearchViewModel = hiltViewModel(),
    navigator: SearchNavigator
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    SearchPage(
        state = state,
        onAction = viewModel::onAction,
        navigator = navigator
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchPage(
    state: SearchState,
    onAction: (SearchAction) -> Unit,
    navigator: SearchNavigator
) {
    val lazyListPaddingValue = 10.dp

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            SearchHeaderView(
                searchValue = state.searchValue,
                onSearchValueChange = { onAction(SearchAction.OnQueryChange(it)) }
            )
        }
    ) { innerPadding ->
        AnimatedContent(
            modifier = Modifier.padding(innerPadding),
            targetState = state.searchValue.isEmpty(),
            transitionSpec = { fadeIn().togetherWith(fadeOut()) }
        ) { isDiscoveryVisible ->
            if (isDiscoveryVisible) {
                MainPageLazyColumn(
                    modifier = Modifier,
                    lazyListPaddingValue = lazyListPaddingValue
                ) {
                    discoverySectionView(
                        discovery = state.discovery,
                        onMovieClick = navigator.navigateToMovieDetails
                    )
                }
            } else {
                MainPageLazyColumn(
                    modifier = Modifier,
                    lazyListPaddingValue = lazyListPaddingValue
                ) {
                    searchSectionView(
                        cast = state.cast,
                        movies = state.movies,
                        tvShows = state.tvShows
                    )
                }
            }
        }
    }
}

fun LazyListScope.discoverySectionView(discovery: List<MovieDomain>, onMovieClick: (Int) -> Unit) {
    item {
        Text(
            text = "Discovery",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onBackground,
            fontWeight = FontWeight.SemiBold
        )
    }

    items(discovery) { movie ->
        SearchItemView(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(onClick = { onMovieClick(movie.id) }),
            title = movie.title,
            url = movie.posterPath,
            overview = movie.overview,
            voteAverage = movie.voteAverage
        )
    }
}

@OptIn(ExperimentalLayoutApi::class)
fun LazyListScope.searchSectionView(
    cast: List<SearchDomain>,
    movies: List<SearchDomain>,
    tvShows: List<SearchDomain>
) {
    if (cast.isNotEmpty()) {
        item {
            Text(
                text = "Cast",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onBackground,
                fontWeight = FontWeight.SemiBold
            )
        }

        item {
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalNegativePadding(16.dp),
                contentPadding = PaddingValues(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(cast) {
                    Column(
                        modifier = Modifier.width(100.dp),
                        verticalArrangement = Arrangement.spacedBy(4.dp),
                    ) {
                        ImageItemView(
                            modifier = Modifier.width(100.dp),
                            posterPath = it.profilePath
                        )

                        Text(
                            text = it.name,
                            style = MaterialTheme.typography.labelMedium,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    }
                }
            }
        }
    }

    if (movies.isNotEmpty()) {
        item {
            Text(
                text = "Movies",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onBackground,
                fontWeight = FontWeight.SemiBold
            )
        }

        items(movies) {
            SearchItemView(
                modifier = Modifier.fillMaxWidth(),
                title = it.title,
                url = it.posterPath,
                overview = it.overview,
                voteAverage = it.voteAverage
            )
        }
    }

    if (tvShows.isNotEmpty()) {
        item {
            Text(
                text = "Tv Shows",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onBackground,
                fontWeight = FontWeight.SemiBold
            )
        }

        items(tvShows) {
            SearchItemView(
                modifier = Modifier.fillMaxWidth(),
                title = it.title,
                url = it.posterPath,
                overview = it.overview,
                voteAverage = it.voteAverage
            )
        }
    }
}

@PreviewLightDark
@Composable
private fun SearchPagePreview() {
    ComposeCourseTheme {
        SearchPage(
            state = SearchState(),
            onAction = {},
            navigator = SearchNavigator()
        )
    }
}
