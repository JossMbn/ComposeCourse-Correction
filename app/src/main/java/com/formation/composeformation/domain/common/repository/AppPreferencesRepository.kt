package com.formation.composeformation.domain.common.repository

import com.formation.composeformation.domain.common.model.AppPreferences
import com.formation.composeformation.domain.common.model.AppTheme
import kotlinx.coroutines.flow.Flow

interface AppPreferencesRepository {
    val appPreferencesFlow: Flow<AppPreferences>

    suspend fun updateTheme(theme: AppTheme)

    suspend fun updateOnBoardingCompletion(isCompleted: Boolean)
}
