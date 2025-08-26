package com.formation.composecourse.data.common.repository

import com.formation.composecourse.data.common.source.endpoint.AppPreferencesDataSource
import com.formation.composecourse.domain.common.model.AppPreferences
import com.formation.composecourse.domain.common.model.AppTheme
import com.formation.composecourse.domain.common.repository.AppPreferencesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AppPreferencesRepositoryImpl @Inject constructor(
    private val appPreferencesDataSource: AppPreferencesDataSource
) : AppPreferencesRepository {

    override val appPreferencesFlow: Flow<AppPreferences>
        get() = appPreferencesDataSource.appPreferencesFlow

    override suspend fun updateTheme(theme: AppTheme) {
        appPreferencesDataSource.updateTheme(theme = theme)
    }

    override suspend fun updateOnBoardingCompletion(isCompleted: Boolean) {
        appPreferencesDataSource.updateOnBoardingCompletion(isCompleted = isCompleted)
    }
}
