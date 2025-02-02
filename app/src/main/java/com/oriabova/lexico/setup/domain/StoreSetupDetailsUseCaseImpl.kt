package com.oriabova.lexico.setup.domain

import com.oriabova.lexico.setup.data.SetupDetails
import javax.inject.Inject

class StoreSetupDetailsUseCaseImpl @Inject constructor(
    private val setupRepository: SetupRepository
) : StoreSetupDetailsUseCase {
    override suspend operator fun invoke(setupDetails: SetupDetails) {
        setupRepository.storeSetupDetails(setupDetails)
    }
}