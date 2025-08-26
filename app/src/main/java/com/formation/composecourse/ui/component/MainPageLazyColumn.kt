package com.formation.composecourse.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainPageLazyColumn(
    modifier: Modifier = Modifier,
    lazyListPaddingValue: Dp,
    content: LazyListScope.() -> Unit
) {
    val lazyListState = rememberLazyListState()

    LazyColumn(
        modifier = modifier
            .fillMaxSize(),
        state = lazyListState,
        contentPadding = PaddingValues(lazyListPaddingValue),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        content()
    }
}
