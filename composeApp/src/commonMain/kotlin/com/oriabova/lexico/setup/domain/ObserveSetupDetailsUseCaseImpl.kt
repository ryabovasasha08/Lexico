package com.oriabova.lexico.setup.domain

import com.oriabova.lexico.setup.domain.model.SetupDetails
import kotlinx.coroutines.flow.Flow

class ObserveSetupDetailsUseCaseImpl(
    private val setupRepository: SetupRepository
) : ObserveSetupDetailsUseCase {
    override operator fun invoke(): Flow<SetupDetails> {
        return setupRepository.observeSetupDetails()
    }
}