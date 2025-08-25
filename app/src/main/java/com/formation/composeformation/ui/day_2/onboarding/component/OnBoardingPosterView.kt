package com.formation.composeformation.ui.day_2.onboarding.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.formation.composeformation.R
import com.formation.composeformation.designsystem.theme.ComposeFormationTheme

@Composable
fun OnBoardingPosterView(
    modifier: Modifier = Modifier,
    @DrawableRes posterId: Int
) {
    Box(modifier = modifier) {
        Image(
            modifier = Modifier.matchParentSize(),
            painter = painterResource(id = posterId),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )

        Box(
            modifier = Modifier
                .matchParentSize()
                .background(Color.Black.copy(alpha = 0.2f))
        )
    }
}

@Preview
@Composable
private fun OnBoardingPosterViewPreview() {
    ComposeFormationTheme {
        OnBoardingPosterView(
            modifier = Modifier.fillMaxSize(),
            posterId = R.drawable.iron_man_poster
        )
    }
}
