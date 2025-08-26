package com.formation.composecourse.designsystem.component

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowLeft
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HeaderView(
    modifier: Modifier = Modifier,
    title: String = "",
    onNavigationClick: (() -> Unit)? = null,
    actions: @Composable RowScope.() -> Unit = {},
    colors: TopAppBarColors = topAppBarColors(
        containerColor = Color.White,
        titleContentColor = Color.Black
    )
) {
    CenterAlignedTopAppBar(
        modifier = modifier,
        title = {
            title.takeIf { it.isNotEmpty() }?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.SemiBold
                )
            }
        },
        navigationIcon = {
            onNavigationClick?.let {
                IconButton(onClick = it) {
                    Icon(
                        modifier = Modifier.size(38.dp),
                        imageVector = Icons.AutoMirrored.Rounded.KeyboardArrowLeft,
                        contentDescription = null
                    )
                }
            }
        },
        actions = actions,
        colors = colors
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
private fun HeaderViewPreview() {
    HeaderView(
        title = "Matrix",
        onNavigationClick = {},
    )
}
