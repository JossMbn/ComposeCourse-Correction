package com.formation.composecourse.ui.detail

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.formation.composecourse.designsystem.component.HeaderView
import com.formation.composecourse.designsystem.theme.ComposeFormationTheme
import com.formation.composecourse.designsystem.utils.RemoveDefaultPadding
import com.formation.composecourse.ui.detail.component.ExpandableText
import com.formation.composecourse.ui.detail.component.PersonLayoutView
import com.formation.composecourse.ui.detail.model.MovieDetailsContentView
import com.formation.composecourse.ui.detail.model.MovieDetailsState
import com.formation.composecourse.ui.detail.navigation.MovieDetailsNavigator
import com.formation.composecourse.ui.utils.horizontalNegativePadding

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MovieDetailsRoot(
    viewModel: MovieDetailsViewModel = hiltViewModel(),
    navigator: MovieDetailsNavigator
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val lazyListState = rememberLazyListState()
    val isBackDropImageVisible by remember {
        derivedStateOf {
            lazyListState.firstVisibleItemIndex == 0
        }
    }
    val headerColor by animateColorAsState(
        targetValue = if (isBackDropImageVisible) Color.Transparent else MaterialTheme.colorScheme.surface
    )
    val headerTitle = if (isBackDropImageVisible) "" else state.movieDetails?.title ?: ""

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            HeaderView(
                onNavigationClick = navigator.navigateBack,
                title = headerTitle,
                colors = topAppBarColors(containerColor = headerColor)
            )
        }
    ) {
        AnimatedContent(
            targetState = state.contentView,
            transitionSpec = { fadeIn().togetherWith(fadeOut()) }
        ) {
            when (it) {
                MovieDetailsContentView.Loading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(
                            color = MaterialTheme.colorScheme.primary,
                            strokeWidth = 1.dp,
                            strokeCap = StrokeCap.Round
                        )
                    }
                }

                MovieDetailsContentView.Content -> MovieDetailsPage(
                    state = state,
                    lazyListState = lazyListState
                )
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun MovieDetailsPage(state: MovieDetailsState, lazyListState: LazyListState) {
    val uriHandler = LocalUriHandler.current
    val imageGradient = listOf(
        MaterialTheme.colorScheme.background.copy(alpha = 0.2f),
        MaterialTheme.colorScheme.background.copy(alpha = 0.4f),
        MaterialTheme.colorScheme.background.copy(alpha = 0.6f),
        MaterialTheme.colorScheme.background.copy(alpha = 0.8f),
        MaterialTheme.colorScheme.background
    )

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        state = lazyListState,
        contentPadding = PaddingValues(
            start = 16.dp,
            end = 16.dp,
            bottom = 16.dp
        ),
        verticalArrangement = Arrangement.spacedBy(20.dp),
    ) {
        item {
            Box(
                modifier = Modifier
                    .horizontalNegativePadding(16.dp)
                    .drawWithContent {
                        drawContent()
                        drawRect(Brush.verticalGradient(imageGradient))
                    },
                contentAlignment = Alignment.Center
            ) {
                AsyncImage(
                    modifier = Modifier.fillMaxWidth(),
                    model = state.movieDetails?.backdropPath,
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )
            }
        }

        item {
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = buildAnnotatedString {
                    withStyle(
                        style = MaterialTheme.typography.titleLarge.toSpanStyle().copy(
                            fontWeight = FontWeight.Bold
                        ),
                    ) {
                        append(state.movieDetails?.title ?: "")
                    }
                    state.movieYear?.let { year ->
                        withStyle(
                            style = MaterialTheme.typography.titleMedium.toSpanStyle().copy(
                                fontWeight = FontWeight.Normal
                            ),
                        ) {
                            append(" • $year")
                        }
                    }
                },
                color = MaterialTheme.colorScheme.onBackground,
            )
        }

        state.movieDetails?.overview?.let { overview ->
            item {
                ExpandableText(
                    text = overview,
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.Normal,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
        }

        state.movieDetails?.website?.let { websiteUrl ->
            item {
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(6.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.onPrimary
                    ),
                    onClick = { uriHandler.openUri(websiteUrl) }
                ) {
                    Text(
                        text = "Visit website",
                        style = MaterialTheme.typography.bodySmall,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }

        state.movieDetails?.genres?.let { genres ->
            item {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Genres",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }

            item {
                FlowRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.Start),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    genres.forEach { genre ->
                        RemoveDefaultPadding {
                            AssistChip(
                                label = {
                                    Text(text = genre.name)
                                },
                                onClick = {}
                            )
                        }
                    }
                }
            }
        }

        state.credits?.actors?.takeIf { it.isNotEmpty() }?.let { actors ->
            item {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Cast",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }

            item {
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalNegativePadding(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    contentPadding = PaddingValues(horizontal = 16.dp)
                ) {
                    items(actors) { actor ->
                        PersonLayoutView(item = actor)
                    }
                }
            }
        }

        state.credits?.directing?.takeIf { it.isNotEmpty() }?.let { directors ->
            item {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Directing",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }

            item {
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalNegativePadding(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    contentPadding = PaddingValues(horizontal = 16.dp)
                ) {
                    items(directors) { director ->
                        PersonLayoutView(item = director)
                    }
                }
            }
        }

        state.credits?.production?.takeIf { it.isNotEmpty() }?.let { production ->
            item {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Production",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }

            item {
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalNegativePadding(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    contentPadding = PaddingValues(horizontal = 16.dp)
                ) {
                    items(production) { item ->
                        PersonLayoutView(item = item)
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun MovieDetailsPagePreview() {
    ComposeFormationTheme {
        MovieDetailsPage(
            state = MovieDetailsState(),
            lazyListState = rememberLazyListState()
        )
    }
}
