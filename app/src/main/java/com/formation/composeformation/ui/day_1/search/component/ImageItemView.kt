package com.formation.composeformation.ui.day_1.search.component

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun ImageItemView(
    modifier: Modifier = Modifier,
    posterPath: String
) {
    AsyncImage(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp)),
        model = posterPath,
        contentDescription = null,
        contentScale = ContentScale.Crop
    )
}
