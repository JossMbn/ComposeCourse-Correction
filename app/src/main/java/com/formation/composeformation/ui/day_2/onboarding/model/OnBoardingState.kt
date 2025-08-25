package com.formation.composeformation.ui.day_2.onboarding.model

import com.formation.composeformation.R

data class OnBoardingState(
    val currentPage: Int = 0
) {
    val title: String = when (currentPage) {
        0 -> "If You Looking For The Latest Movies"
        1 -> "Welcome To My Movie App"
        else -> "Only With Us, Only For You"
    }

    val subtitle: String = when (currentPage) {
        0 -> "You are at the right place"
        1 -> "We have a wide range of tv shows, series and movies to watch"
        else -> "Are you ready to watch?"
    }

    val buttonText: String
        get() {
            return when (currentPage) {
                in 0..1 -> "Next"
                else -> "Start"
            }
        }

    val posterId: Int = when (currentPage) {
        0 -> R.drawable.iron_man_poster
        1 -> R.drawable.the_batman_poster
        else -> R.drawable.dune_poster
    }
}
