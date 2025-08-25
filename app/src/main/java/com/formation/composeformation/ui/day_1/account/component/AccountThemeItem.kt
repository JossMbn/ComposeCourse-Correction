package com.formation.composeformation.ui.day_1.account.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.formation.composeformation.domain.common.model.AppTheme

@Composable
fun AccountThemeItem(
    modifier: Modifier = Modifier,
    isSelected: Boolean,
    theme: AppTheme,
    onClick: () -> Unit
) {
    val shape = RoundedCornerShape(6.dp)
    val backgroundColor = if (isSelected) {
        MaterialTheme.colorScheme.primary
    } else {
        Color.Transparent
    }

    val textColor = if (isSelected) {
        MaterialTheme.colorScheme.onPrimary
    } else {
        MaterialTheme.colorScheme.onSurfaceVariant
    }

    Box(
        modifier = modifier
            .clip(shape)
            .border(width = 1.dp, color = MaterialTheme.colorScheme.outline, shape = shape)
            .background(backgroundColor)
            .clickable(onClick = onClick)
            .padding(vertical = 10.dp, horizontal = 14.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = theme.name,
            style = MaterialTheme.typography.bodyMedium,
            color = textColor
        )
    }
}
