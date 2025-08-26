package com.formation.composecourse.data.common.source.endpoint

import androidx.datastore.core.DataStore
import com.formation.composecourse.domain.common.model.AppPreferences
import com.formation.composecourse.domain.common.model.AppTheme
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface AppPreferencesDataSource {
    val appPreferencesFlow: Flow<AppPreferences>

    suspend fun updateTheme(theme: AppTheme)

    suspend fun updateOnBoardingCompletion(isCompleted: Boolean)
}

class AppPreferencesDataSourceImpl @Inject constructor(
    private val dataStore: DataStore<AppPreferences>
) : AppPreferencesDataSource {

    override val appPreferencesFlow: Flow<AppPreferences>
        get() = dataStore.data

    override suspend fun updateTheme(theme: AppTheme) {
        dataStore.updateData { it.copy(theme = theme) }
    }

    override suspend fun updateOnBoardingCompletion(isCompleted: Boolean) {
        dataStore.updateData { it.copy(isOnBoardingCompleted = isCompleted) }
    }
}
