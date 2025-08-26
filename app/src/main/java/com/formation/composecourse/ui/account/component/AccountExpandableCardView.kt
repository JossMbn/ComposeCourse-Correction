package com.formation.composecourse.ui.account.component

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.formation.composecourse.designsystem.theme.ComposeFormationTheme

@Composable
fun AccountExpandableCardView(
    modifier: Modifier = Modifier,
    title: String,
    content: @Composable ColumnScope.() -> Unit
) {
    var isExpanded by rememberSaveable { mutableStateOf(false) }
    val rotation by animateFloatAsState(targetValue = if (isExpanded) 180f else 0f)

    Column(
        modifier = modifier
            .animateContentSize()
            .clip(shape = RoundedCornerShape(6.dp))
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .clickable(onClick = { isExpanded = !isExpanded })
            .padding(vertical = 18.dp, horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = modifier,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Icon(
                modifier = Modifier.graphicsLayer {
                    rotationZ = rotation
                },
                imageVector = Icons.Rounded.KeyboardArrowDown,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        if (isExpanded) {
            content()
        }
    }
}

@Preview
@Composable
private fun AccountExpandableCardViewPreview() {
    ComposeFormationTheme {
        AccountExpandableCardView(
            modifier = Modifier.fillMaxWidth(),
            title = "Expandable Card"
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Expandable card description",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}
