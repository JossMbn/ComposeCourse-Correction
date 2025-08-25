package com.formation.composeformation.ui.day_1.home.home.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HomeSectionRowView(
    sectionTitle: String,
    sectionListItemsView: LazyListScope.() -> Unit
) {
    val lazyListState = rememberLazyListState()
    val density = LocalDensity.current
    var maxLayoutSize by remember { mutableStateOf(0.dp) }

    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Text(
            modifier = Modifier.padding(horizontal = 16.dp),
            text = sectionTitle,
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.onBackground
        )

        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = maxLayoutSize)
                .onSizeChanged {
                    // Calculate the maximum item height and apply it to the LazyRow
                    // because some item have a different height
                    // and make the below lazy list shake when scrolling
                    with(density) {
                        if (it.height.toDp() > maxLayoutSize) {
                            maxLayoutSize = it.height.toDp()
                        }
                    }
                },
            state = lazyListState,
            contentPadding = PaddingValues(vertical = 10.dp, horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            sectionListItemsView()
        }
    }
}
