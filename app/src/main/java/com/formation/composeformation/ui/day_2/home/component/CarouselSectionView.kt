package com.formation.composeformation.ui.day_2.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun CarouselSectionView(
    modifier: Modifier = Modifier,
    title: String,
    onSeeAllClick: () -> Unit,
    content: @Composable () -> Unit
) {
    Column(modifier = modifier) {
        Row(
            modifier = modifier,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onBackground,
                fontWeight = FontWeight.SemiBold
            )

            TextButton(onClick = onSeeAllClick) {
                Text(
                    modifier = Modifier,
                    text = "See all",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onBackground,
                    fontWeight = FontWeight.Normal
                )
            }
        }

        content()
    }
}

@Preview
@Composable
private fun CarouselSectionViewPreview() {
    CarouselSectionView(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background),
        title = "Popular",
        onSeeAllClick = {}
    ) {
        Text(text = "Content")
    }
}
