package com.formation.composecourse.ui.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.formation.composecourse.designsystem.component.HeaderView
import com.formation.composecourse.ui.home.model.FilterSection

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeHeaderView(
    currentFilterSection: FilterSection,
    onClick: (FilterSection) -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        HeaderView(
            modifier = Modifier.fillMaxWidth(),
            title = "Home",
            colors = topAppBarColors(
                containerColor = MaterialTheme.colorScheme.background,
                scrolledContainerColor = MaterialTheme.colorScheme.background,
                titleContentColor = MaterialTheme.colorScheme.onBackground
            )
        )

        FilterRowSection(
            modifier = Modifier
                .fillMaxWidth(),
            currentFilterSection = currentFilterSection,
            onClick = onClick
        )
    }
}

@Composable
private fun FilterRowSection(
    modifier: Modifier = Modifier,
    currentFilterSection: FilterSection,
    onClick: (FilterSection) -> Unit
) {
    Row(
        modifier = modifier
            .background(MaterialTheme.colorScheme.background)
            .padding(vertical = 8.dp, horizontal = 4.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        FilterTextButton(
            text = "Movies",
            isSelected = currentFilterSection == FilterSection.Movies,
            onClick = { onClick(FilterSection.Movies) }
        )

        FilterTextButton(
            text = "Tv Shows",
            isSelected = currentFilterSection == FilterSection.TvShows,
            onClick = { onClick(FilterSection.TvShows) }
        )
    }
}
