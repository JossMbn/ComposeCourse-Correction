package com.formation.composeformation.domain.common.usecase

import com.formation.composeformation.domain.common.repository.AppPreferencesRepository
import javax.inject.Inject

class UpdateOnBoardingCompletionUseCase @Inject constructor(
    private val appPreferencesRepository: AppPreferencesRepository
) {
    suspend operator fun invoke(isCompleted: Boolean) {
        appPreferencesRepository.updateOnBoardingCompletion(isCompleted = isCompleted)
    }
}
