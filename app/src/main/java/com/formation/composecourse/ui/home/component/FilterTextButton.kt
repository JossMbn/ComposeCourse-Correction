package com.formation.composecourse.ui.home.component

import androidx.compose.foundation.background
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun FilterTextButton(
    modifier: Modifier = Modifier,
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit,
) {
    val fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal

    TextButton(
        modifier = modifier,
        onClick = onClick,
        colors = ButtonDefaults.textButtonColors(contentColor = MaterialTheme.colorScheme.onBackground)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = fontWeight
        )
    }
}

@Preview
@Composable
private fun FilterTextButtonPreview() {
    FilterTextButton(
        modifier = Modifier.background(MaterialTheme.colorScheme.background),
        text = "Tv Shows",
        isSelected = true,
        onClick = {}
    )
}
