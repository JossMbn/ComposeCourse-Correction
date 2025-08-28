package com.formation.composecourse.ui.home

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.formation.composecourse.designsystem.theme.ComposeCourseTheme
import com.formation.composecourse.ui.component.MainPageLazyColumn
import com.formation.composecourse.ui.home.component.CarouselSectionView
import com.formation.composecourse.ui.home.component.HomeHeaderView
import com.formation.composecourse.ui.home.component.PosterCarouselView
import com.formation.composecourse.ui.home.component.TvShowHeadlineCarouselView
import com.formation.composecourse.ui.home.model.FilterSection
import com.formation.composecourse.ui.home.model.HomeAction
import com.formation.composecourse.ui.home.model.HomeState
import com.formation.composecourse.ui.home.navigation.HomeNavigator
import com.formation.composecourse.ui.utils.horizontalNegativePadding

@Composable
fun HomeRoot(
    viewModel: HomeViewModel = hiltViewModel(),
    navigator: HomeNavigator
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    HomePage(
        state = state,
        onAction = viewModel::onAction,
        navigator = navigator
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePage(
    state: HomeState,
    onAction: (HomeAction) -> Unit,
    navigator: HomeNavigator
) {
    val lazyListPaddingValue = 14.dp

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            HomeHeaderView(
                currentFilterSection = state.currentFilterSection,
                onClick = { onAction(HomeAction.OnFilterSectionClick(it)) }
            )
        }
    ) { innerPadding ->
        AnimatedContent(
            modifier = Modifier.padding(innerPadding),
            targetState = state.currentFilterSection,
            transitionSpec = { fadeIn().togetherWith(fadeOut()) }
        ) { currentFilterSection ->
            when (currentFilterSection) {
                FilterSection.Movies -> {
                    MainPageLazyColumn(
                        lazyListPaddingValue = lazyListPaddingValue
                    ) {
                        movieSectionView(
                            state = state,
                            onAction = onAction,
                            navigator = navigator,
                            lazyListPaddingValue = lazyListPaddingValue
                        )
                    }
                }

                FilterSection.TvShows -> {
                    MainPageLazyColumn(
                        lazyListPaddingValue = lazyListPaddingValue
                    ) {
                        tvShowsSectionView(
                            state = state,
                            onAction = onAction,
                            lazyListPaddingValue = lazyListPaddingValue
                        )
                    }
                }
            }
        }
    }
}

private fun LazyListScope.movieSectionView(
    state: HomeState,
    onAction: (HomeAction) -> Unit,
    navigator: HomeNavigator,
    lazyListPaddingValue: Dp
) {
    item {
        CarouselSectionView(
            modifier = Modifier.fillMaxWidth(),
            title = "Now Playing"
        ) {
            PosterCarouselView(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalNegativePadding(lazyListPaddingValue),
                items = state.movieSection?.nowPlaying,
                onItemClick = { navigator.navigateToMovieDetails(it) }
            )
        }
    }

    item {
        CarouselSectionView(
            modifier = Modifier.fillMaxWidth(),
            title = "Popular"
        ) {
            PosterCarouselView(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalNegativePadding(lazyListPaddingValue),
                items = state.movieSection?.popular,
                onItemClick = { navigator.navigateToMovieDetails(it) }
            )
        }
    }

    item {
        CarouselSectionView(
            modifier = Modifier.fillMaxWidth(),
            title = "Top Rated"
        ) {
            PosterCarouselView(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalNegativePadding(lazyListPaddingValue),
                items = state.movieSection?.topRated,
                onItemClick = { navigator.navigateToMovieDetails(it) }
            )
        }
    }

    item {
        CarouselSectionView(
            modifier = Modifier.fillMaxWidth(),
            title = "Upcoming"
        ) {
            PosterCarouselView(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalNegativePadding(lazyListPaddingValue),
                items = state.movieSection?.upcoming,
                onItemClick = { navigator.navigateToMovieDetails(it) }
            )
        }
    }
}

private fun LazyListScope.tvShowsSectionView(
    state: HomeState,
    onAction: (HomeAction) -> Unit,
    lazyListPaddingValue: Dp
) {
    state.tvShowSection?.headlineTvShow?.let { headlineTvShow ->
        item {
            TvShowHeadlineCarouselView(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalNegativePadding(lazyListPaddingValue),
                headlineTvShow = headlineTvShow
            )
        }
    }

    item {
        CarouselSectionView(
            modifier = Modifier.fillMaxWidth(),
            title = "Airing Now"
        ) {
            PosterCarouselView(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalNegativePadding(lazyListPaddingValue),
                items = state.tvShowSection?.airingToday,
                onItemClick = {}
            )
        }
    }

    item {
        CarouselSectionView(
            modifier = Modifier.fillMaxWidth(),
            title = "On the Air"
        ) {
            PosterCarouselView(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalNegativePadding(lazyListPaddingValue),
                items = state.tvShowSection?.onTheAir,
                onItemClick = {}
            )
        }
    }

    item {
        CarouselSectionView(
            modifier = Modifier.fillMaxWidth(),
            title = "Popular"
        ) {
            PosterCarouselView(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalNegativePadding(lazyListPaddingValue),
                items = state.tvShowSection?.popular,
                onItemClick = {}
            )
        }
    }

    item {
        CarouselSectionView(
            modifier = Modifier.fillMaxWidth(),
            title = "Top Rated"
        ) {
            PosterCarouselView(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalNegativePadding(lazyListPaddingValue),
                items = state.tvShowSection?.topRated,
                onItemClick = {}
            )
        }
    }
}

@Preview
@Composable
private fun HomePagePreview() {
    ComposeCourseTheme {
        HomePage(
            state = HomeState(),
            onAction = {},
            navigator = HomeNavigator()
        )
    }
}
