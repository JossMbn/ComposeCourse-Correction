package com.formation.composeformation.ui.day_1.account.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.PhotoCamera
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.formation.composeformation.R
import com.formation.composeformation.designsystem.theme.ComposeFormationTheme

@Composable
fun ProfileView(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(6.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ProfileImageView()

        Text(
            text = stringResource(R.string.dev_full_name),
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground
        )

        Text(
            text = stringResource(R.string.dev_email),
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}

@Composable
fun ProfileImageView(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.BottomEnd
    ) {
        Icon(
            modifier = Modifier.size(80.dp),
            imageVector = Icons.Rounded.AccountCircle,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onBackground
        )

        Box(
            modifier = Modifier
                .offset(x = (-3).dp, y = (-3).dp)
                .clip(CircleShape)
                .border(width = 1.dp, color = Color.White, shape = CircleShape)
                .background(Color.Black)
                .padding(4.dp),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                modifier = Modifier
                    .size(16.dp),
                imageVector = Icons.Outlined.PhotoCamera,
                contentDescription = null,
                tint = Color.White
            )
        }
    }
}

@Preview
@Composable
private fun ProfileViewPreview() {
    ComposeFormationTheme {
        ProfileView(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .padding(10.dp)
        )
    }
}
