package com.formation.composecourse.ui.onboarding.model

import com.formation.composecourse.R

enum class PosterData(
    val posterId: Int,
    val title: Int,
    val subtitle: Int
) {
    IronMan(
        posterId = R.drawable.iron_man_poster,
        title = R.string.on_boarding_iron_man_title,
        subtitle = R.string.on_boarding_iron_man_subtitle
    ),
    Batman(
        posterId = R.drawable.the_batman_poster,
        title = R.string.on_boarding_batman_title,
        subtitle = R.string.on_boarding_batman_subtitle
    ),
    Dune(
        posterId = R.drawable.dune_poster,
        title = R.string.on_boarding_dune_title,
        subtitle = R.string.on_boarding_dune_subtitle
    )
}

data class OnBoardingState(
    val poster: PosterData = PosterData.IronMan
)
