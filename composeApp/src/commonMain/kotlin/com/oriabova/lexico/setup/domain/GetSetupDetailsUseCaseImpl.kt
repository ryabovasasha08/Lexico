package com.oriabova.lexico.setup.domain

import com.oriabova.lexico.setup.domain.model.SetupDetails

class GetSetupDetailsUseCaseImpl(
    private val setupRepository: SetupRepository
) : GetSetupDetailsUseCase {
    override suspend operator fun invoke(): SetupDetails {
        return setupRepository.getSetupDetails()
    }
}