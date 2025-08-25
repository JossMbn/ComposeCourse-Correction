package com.formation.composeformation.designsystem.utils

import androidx.compose.material3.LocalMinimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.unit.dp

@Composable
fun RemoveDefaultPadding(content: @Composable () -> Unit) {
    // CompositionLocalProvider is used to override the value of LocalMinimumInteractiveComponentSize
    // and remove default Material 3 padding.
    CompositionLocalProvider(
        LocalMinimumInteractiveComponentSize provides 0.dp,
        content = content
    )
}
