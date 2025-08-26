package com.formation.composecourse.ui.detail.component

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ExpandableText(
    modifier: Modifier = Modifier,
    text: String,
    style: TextStyle = MaterialTheme.typography.bodySmall,
    fontWeight: FontWeight = FontWeight.Normal,
    color: Color = MaterialTheme.colorScheme.onBackground
) {
    var showMore by remember { mutableStateOf(false) }
    var didOverflow by remember { mutableStateOf(false) }

    FlowRow(
        modifier = modifier
            .fillMaxWidth()
            .animateContentSize(animationSpec = tween(durationMillis = 150, easing = LinearEasing)),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalArrangement = Arrangement.spacedBy(0.dp)
    ) {
        Text(
            modifier = Modifier,
            text = text,
            style = style,
            fontWeight = fontWeight,
            color = color,
            maxLines = if (showMore) Int.MAX_VALUE else 4,
            overflow = if (showMore) TextOverflow.Clip else TextOverflow.Ellipsis,
            onTextLayout = { textLayoutResult ->
                didOverflow = textLayoutResult.didOverflowHeight
            }
        )

        if (!showMore && didOverflow) {
            Text(
                modifier = Modifier
                    .clickable(
                        onClick = { showMore = !showMore },
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null
                    ),
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground,
                text = "Read more"
            )
        }
    }
}
