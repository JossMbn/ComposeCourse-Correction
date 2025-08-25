package com.formation.composeformation.ui.day_2.home

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.formation.composeformation.designsystem.theme.ComposeFormationTheme
import com.formation.composeformation.ui.day_2.component.MainPageLazyColumn
import com.formation.composeformation.ui.day_2.home.component.CarouselSectionView
import com.formation.composeformation.ui.day_2.home.component.HomeHeaderView
import com.formation.composeformation.ui.day_2.home.component.PosterCarouselView
import com.formation.composeformation.ui.day_2.home.component.TvShowHeadlineCarouselView
import com.formation.composeformation.ui.day_2.home.model.FilterSection
import com.formation.composeformation.ui.day_2.home.model.HomeDay2Action
import com.formation.composeformation.ui.day_2.home.model.HomeDay2State
import com.formation.composeformation.ui.day_2.home.navigation.HomeDay2Navigator
import com.formation.composeformation.ui.utils.horizontalNegativePadding

@Composable
fun HomeDay2Root(
    viewModel: HomeDay2ViewModel = hiltViewModel(),
    navigator: HomeDay2Navigator
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    HomeDay2Page(
        state = state,
        onAction = viewModel::onAction,
        navigator = navigator
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeDay2Page(
    state: HomeDay2State,
    onAction: (HomeDay2Action) -> Unit,
    navigator: HomeDay2Navigator
) {
    val lazyListPaddingValue = 10.dp
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            HomeHeaderView(
                currentFilterSection = state.currentFilterSection,
                scrollBehavior = scrollBehavior,
                onClick = { onAction(HomeDay2Action.OnFilterSectionClick(it)) }
            )
        }
    ) { innerPadding ->
        AnimatedContent(
            targetState = state.currentFilterSection,
            transitionSpec = { fadeIn().togetherWith(fadeOut()) }
        ) { currentFilterSection ->
            when (currentFilterSection) {
                FilterSection.Movies -> {
                    MainPageLazyColumn(
                        innerPadding = innerPadding,
                        scrollBehavior = scrollBehavior,
                        lazyListPaddingValue = lazyListPaddingValue
                    ) {
                        movieSectionView(
                            state = state,
                            onAction = onAction,
                            lazyListPaddingValue = lazyListPaddingValue
                        )
                    }
                }

                FilterSection.TvShows -> {
                    MainPageLazyColumn(
                        innerPadding = innerPadding,
                        scrollBehavior = scrollBehavior,
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
    state: HomeDay2State,
    onAction: (HomeDay2Action) -> Unit,
    lazyListPaddingValue: Dp
) {
    item {
        CarouselSectionView(
            modifier = Modifier.fillMaxWidth(),
            title = "Now Playing",
            onSeeAllClick = {}
        ) {
            PosterCarouselView(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalNegativePadding(lazyListPaddingValue),
                items = state.movieSection?.nowPlaying
            )
        }
    }

    item {
        CarouselSectionView(
            modifier = Modifier.fillMaxWidth(),
            title = "Popular",
            onSeeAllClick = {}
        ) {
            PosterCarouselView(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalNegativePadding(lazyListPaddingValue),
                items = state.movieSection?.popular
            )
        }
    }

    item {
        CarouselSectionView(
            modifier = Modifier.fillMaxWidth(),
            title = "Top Rated",
            onSeeAllClick = {}
        ) {
            PosterCarouselView(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalNegativePadding(lazyListPaddingValue),
                items = state.movieSection?.topRated
            )
        }
    }

    item {
        CarouselSectionView(
            modifier = Modifier.fillMaxWidth(),
            title = "Upcoming",
            onSeeAllClick = {}
        ) {
            PosterCarouselView(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalNegativePadding(lazyListPaddingValue),
                items = state.movieSection?.upcoming
            )
        }
    }
}

private fun LazyListScope.tvShowsSectionView(
    state: HomeDay2State,
    onAction: (HomeDay2Action) -> Unit,
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
            title = "Airing Now",
            onSeeAllClick = {}
        ) {
            PosterCarouselView(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalNegativePadding(lazyListPaddingValue),
                items = state.tvShowSection?.airingToday
            )
        }
    }

    item {
        CarouselSectionView(
            modifier = Modifier.fillMaxWidth(),
            title = "On the Air",
            onSeeAllClick = {}
        ) {
            PosterCarouselView(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalNegativePadding(lazyListPaddingValue),
                items = state.tvShowSection?.onTheAir
            )
        }
    }

    item {
        CarouselSectionView(
            modifier = Modifier.fillMaxWidth(),
            title = "Popular",
            onSeeAllClick = {}
        ) {
            PosterCarouselView(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalNegativePadding(lazyListPaddingValue),
                items = state.tvShowSection?.popular
            )
        }
    }

    item {
        CarouselSectionView(
            modifier = Modifier.fillMaxWidth(),
            title = "Top Rated",
            onSeeAllClick = {}
        ) {
            PosterCarouselView(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalNegativePadding(lazyListPaddingValue),
                items = state.tvShowSection?.topRated
            )
        }
    }
}

@Preview
@Composable
private fun HomeDay2PagePreview() {
    ComposeFormationTheme {
        HomeDay2Page(
            state = HomeDay2State(),
            onAction = {},
            navigator = HomeDay2Navigator()
        )
    }
}
