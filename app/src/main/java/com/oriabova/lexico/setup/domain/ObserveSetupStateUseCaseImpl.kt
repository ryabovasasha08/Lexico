package com.oriabova.lexico.setup.domain

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ObserveSetupStateUseCaseImpl @Inject constructor(
    private val setupRepository: SetupRepository
) : ObserveSetupStateUseCase {
   override operator fun invoke(): Flow<SetupState> {
        return setupRepository.observeSetupDetails().map {
            if (it != null) SetupState.COMPLETED else SetupState.NOT_COMPLETED
        }
    }
}