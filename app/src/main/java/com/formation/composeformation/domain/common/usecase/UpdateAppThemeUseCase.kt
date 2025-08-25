package com.formation.composeformation.domain.common.usecase

import com.formation.composeformation.domain.common.model.AppTheme
import com.formation.composeformation.domain.common.repository.AppPreferencesRepository
import javax.inject.Inject

class UpdateAppThemeUseCase @Inject constructor(
    private val appPreferencesRepository: AppPreferencesRepository
) {
    suspend operator fun invoke(theme: AppTheme) {
        appPreferencesRepository.updateTheme(theme = theme)
    }
}
