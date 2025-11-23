package com.oriabova.lexico.setup.domain

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ObserveSetupStateUseCaseImpl(
    private val setupRepository: SetupRepository
) : ObserveSetupStateUseCase {
   override operator fun invoke(): Flow<SetupState> {
        return setupRepository.observeSetupDetails().map {
        SetupState.NOT_COMPLETED
        //if (it != null) SetupState.COMPLETED else SetupState.NOT_COMPLETED
        }
    }
}