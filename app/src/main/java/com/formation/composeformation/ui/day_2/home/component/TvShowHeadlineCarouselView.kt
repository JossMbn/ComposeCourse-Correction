package com.formation.composeformation.ui.day_2.home.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.LocalOverscrollConfiguration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.formation.composeformation.designsystem.theme.ComposeFormationTheme
import com.formation.composeformation.domain.tvshow.model.TvShowDetailsDomain
import java.time.LocalDate

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TvShowHeadlineCarouselView(
    modifier: Modifier = Modifier,
    headlineTvShow: TvShowDetailsDomain
) {
    val state = rememberPagerState { Int.MAX_VALUE }
    var currentPageIndex by remember { mutableIntStateOf(0) }
    val currentSeason by remember {
        derivedStateOf {
            headlineTvShow.seasons.getOrNull(currentPageIndex)
        }
    }

    LaunchedEffect(state.currentPage) {
        currentPageIndex = state.currentPage % headlineTvShow.seasons.size
    }

    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        Box(modifier = Modifier.matchParentSize()) {
            AsyncImage(
                modifier = Modifier.matchParentSize(),
                model = headlineTvShow.backdropPath,
                contentDescription = null,
                contentScale = ContentScale.Crop
            )

            Box(
                modifier = Modifier
                    .matchParentSize()
                    .background(Color.Black.copy(alpha = 0.6f))
            )
        }

        Column(
            modifier = Modifier.padding(vertical = 20.dp),
            verticalArrangement = Arrangement.spacedBy(30.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CompositionLocalProvider(LocalOverscrollConfiguration provides null) {
                HorizontalPager(
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(max = 300.dp),
                    contentPadding = PaddingValues(horizontal = 10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    state = state
                ) { pageIndex ->
                    AsyncImage(
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(RoundedCornerShape(4.dp)),
                        model = headlineTvShow.seasons.getOrNull(pageIndex % headlineTvShow.seasons.size)?.posterPath,
                        contentDescription = null,
                        contentScale = ContentScale.FillHeight
                    )
                }
            }

            Column(
                verticalArrangement = Arrangement.spacedBy(2.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                currentSeason?.name?.let { seasonName ->
                    Text(
                        text = seasonName,
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Normal
                    )
                }

                Text(
                    text = headlineTvShow.name,
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onBackground,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}

@Preview
@Composable
private fun TvShowHeadlineCarouselViewPreview() {
    ComposeFormationTheme {
        TvShowHeadlineCarouselView(
            modifier = Modifier.background(MaterialTheme.colorScheme.background),
            headlineTvShow = TvShowDetailsDomain(
                id = 1,
                name = "Breaking Bad",
                backdropPath = "",
                seasons = emptyList(),
                overview = "",
                posterPath = "",
                voteAverage = 0.0,
                firstAirDate = LocalDate.now()
            )
        )
    }
}
