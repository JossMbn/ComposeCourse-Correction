package com.formation.composecourse.ui.home.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

data class PosterCarouselData(
    val id: Int,
    val name: String,
    val posterPath: String
)

@Composable
fun PosterCarouselView(
    modifier: Modifier = Modifier,
    items: List<PosterCarouselData>?,
    onItemClick: (Int) -> Unit
) {
    val lazyListState = rememberLazyListState()

    LazyRow(
        modifier = modifier.fillMaxWidth(),
        state = lazyListState,
        contentPadding = PaddingValues(horizontal = 10.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        items(items ?: emptyList()) { item ->
            Column(
                modifier = Modifier
                    .width(160.dp)
                    .clickable { onItemClick(item.id) },
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                AsyncImage(
                    modifier = Modifier
                        .width(160.dp)
                        .height(220.dp)
                        .clip(RoundedCornerShape(4.dp)),
                    model = item.posterPath,
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )

                Text(
                    text = item.name,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onBackground,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}
