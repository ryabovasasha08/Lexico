package com.oriabova.lexico.setup.domain

import com.oriabova.lexico.setup.data.SetupDetails

class StoreSetupDetailsUseCaseImpl(
    private val setupRepository: SetupRepository
) : StoreSetupDetailsUseCase {
    override suspend operator fun invoke(setupDetails: SetupDetails) {
        setupRepository.storeSetupDetails(setupDetails)
    }
}