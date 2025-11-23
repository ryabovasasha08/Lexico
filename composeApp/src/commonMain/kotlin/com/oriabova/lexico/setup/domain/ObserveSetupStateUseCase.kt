package com.oriabova.lexico.setup.domain

import kotlinx.coroutines.flow.Flow

interface ObserveSetupStateUseCase {
    operator fun invoke(): Flow<SetupState>
}