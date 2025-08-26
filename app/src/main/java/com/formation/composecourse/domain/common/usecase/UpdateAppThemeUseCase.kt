package com.formation.composecourse.domain.common.usecase

import com.formation.composecourse.domain.common.model.AppTheme
import com.formation.composecourse.domain.common.repository.AppPreferencesRepository
import javax.inject.Inject

class UpdateAppThemeUseCase @Inject constructor(
    private val appPreferencesRepository: AppPreferencesRepository
) {
    suspend operator fun invoke(theme: AppTheme) {
        appPreferencesRepository.updateTheme(theme = theme)
    }
}
