package com.formation.composeformation.domain.common.model

import kotlinx.serialization.Serializable

enum class AppTheme {
    System,
    Dark,
    Light
}

@Serializable
data class AppPreferences(
    val theme: AppTheme = AppTheme.System,
    val isOnBoardingCompleted: Boolean = false
)
