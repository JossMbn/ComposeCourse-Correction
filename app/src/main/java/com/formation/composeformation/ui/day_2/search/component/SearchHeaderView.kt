package com.formation.composeformation.ui.day_2.search.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchHeaderView(
    searchValue: String,
    onSearchValueChange: (String) -> Unit,
    scrollBehavior: TopAppBarScrollBehavior
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        TopAppBar(
            modifier = Modifier.fillMaxWidth(),
            title = {
                Text(
                    text = "Search",
                    style = MaterialTheme.typography.displaySmall,
                    fontWeight = FontWeight.Bold
                )
            },
            colors = topAppBarColors(
                containerColor = MaterialTheme.colorScheme.background,
                scrolledContainerColor = MaterialTheme.colorScheme.background,
                titleContentColor = MaterialTheme.colorScheme.onBackground
            ),
            scrollBehavior = scrollBehavior
        )

        SearchBarView(
            modifier = Modifier
                .padding(vertical = 10.dp, horizontal = 16.dp),
            value = searchValue,
            onValueChange = onSearchValueChange,
            placeholder = "Search for movies, tv shows, people..."
        )
    }
}
