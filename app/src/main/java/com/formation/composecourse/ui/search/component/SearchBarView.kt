package com.formation.composecourse.ui.search.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.formation.composecourse.designsystem.theme.ComposeFormationTheme

@Composable
fun SearchBarView(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String = ""
) {
    BasicTextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        textStyle = MaterialTheme.typography.bodyMedium.copy(
            color = MaterialTheme.colorScheme.onSurfaceVariant
        ),
        cursorBrush = SolidColor(MaterialTheme.colorScheme.onSurfaceVariant),
        maxLines = 1,
        decorationBox = { innerTextField ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(20.dp))
                    .background(MaterialTheme.colorScheme.surfaceVariant)
                    .padding(vertical = 10.dp, horizontal = 20.dp)
            ) {
                if (value.isEmpty()) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = placeholder,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                innerTextField()
            }
        }
    )
}

@Preview
@Composable
private fun SearchBarViewPreview() {
    ComposeFormationTheme {
        SearchBarView(
            value = "Search for a movie, tv show or person",
            onValueChange = {}
        )
    }
}
