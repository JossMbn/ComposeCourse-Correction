package com.formation.composecourse.domain.common.usecase

import com.formation.composecourse.domain.common.model.AppPreferences
import com.formation.composecourse.domain.common.repository.AppPreferencesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveAppPreferencesUseCase @Inject constructor(
    private val appPreferencesRepository: AppPreferencesRepository
) {

    operator fun invoke(): Flow<AppPreferences> {
        return appPreferencesRepository.appPreferencesFlow
    }
}
