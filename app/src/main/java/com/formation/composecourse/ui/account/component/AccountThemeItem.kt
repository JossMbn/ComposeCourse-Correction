package com.formation.composecourse.ui.account.component

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import com.formation.composecourse.R
import com.formation.composecourse.designsystem.theme.ComposeCourseTheme
import com.formation.composecourse.domain.common.model.AppTheme

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

    val borderWidth by animateDpAsState(targetValue = if (selected) 4.dp else 2.dp)
    val borderColor by animateColorAsState(
        targetValue = if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outline
    )

    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(shape)
            .border(width = borderWidth, color = borderColor, shape = shape)
            .clickable(onClick = onClick)
            .padding(vertical = 10.dp, horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        RadioButton(
            selected = selected,
            onClick = onClick
        )

        Text(
            modifier = Modifier.weight(1f),
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface
        )

        icon?.let {
            Icon(
                imageVector = it,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

@Preview
@Composable
private fun AccountThemeItemPreview() {
    ComposeCourseTheme {
        AccountThemeItem(
            modifier = Modifier.background(MaterialTheme.colorScheme.surface),
            selected = true,
            label = AppTheme.Dark.name,
            onClick = {}
        )
    }
}
