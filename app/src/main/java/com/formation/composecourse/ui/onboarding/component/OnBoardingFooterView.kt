package com.formation.composecourse.ui.onboarding.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.formation.composecourse.designsystem.theme.ComposeFormationTheme

@Composable
fun OnBoardingFooterView(
    modifier: Modifier = Modifier,
    title: String,
    subtitle: String,
    buttonText: String,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(14.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = title,
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = subtitle,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold,
            color = Color.LightGray
        )

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = onClick
        ) {
            Text(
                modifier = Modifier.padding(vertical = 4.dp),
                text = buttonText,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onPrimary
            )
        }
    }
}

@Preview
@Composable
fun OnBoardingFooterViewPreview() {
    ComposeFormationTheme {
        OnBoardingFooterView(
            modifier = Modifier.fillMaxWidth(),
            title = "If You Looking For The Latest Movies",
            subtitle = "You are at the right place",
            buttonText = "Next",
            onClick = {}
        )
    }
}
