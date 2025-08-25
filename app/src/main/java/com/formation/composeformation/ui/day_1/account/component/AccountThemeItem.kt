package com.formation.composeformation.ui.day_1.account.component

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.DarkMode
import androidx.compose.material.icons.rounded.LightMode
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.formation.composeformation.R
import com.formation.composeformation.domain.common.model.AppTheme

@Composable
fun AccountThemeItem(
    modifier: Modifier = Modifier,
    selected: Boolean,
    label: String,
    onClick: () -> Unit
) {
    val shape = RoundedCornerShape(16.dp)
    val icon = when (label) {
        AppTheme.System.name -> ImageVector.vectorResource(R.drawable.ic_system_theme)
        AppTheme.Dark.name -> Icons.Rounded.DarkMode
        AppTheme.Light.name -> Icons.Rounded.LightMode
        else -> null
    }

    val textColor = if (selected) {
        MaterialTheme.colorScheme.onSurfaceVariant
    } else {
        MaterialTheme.colorScheme.onSurfaceVariant
    }

    val borderWidth by animateDpAsState(targetValue = if (selected) 4.dp else 2.dp)

    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(shape)
            .border(width = borderWidth, color = MaterialTheme.colorScheme.outline, shape = shape)
            .clickable(onClick = onClick)
            .padding(vertical = 10.dp, horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = selected,
            onClick = onClick
        )

        Text(
            modifier = Modifier.weight(1f),
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            color = textColor
        )

        icon?.let {
            Icon(
                imageVector = it,
                contentDescription = null,
                tint = textColor
            )
        }
    }
}

@Preview
@Composable
private fun AccountThemeItemPreview() {
    AccountThemeItem(
        selected = true,
        label = AppTheme.Dark.name,
        onClick = {}
    )
}
