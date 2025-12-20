package com.oriabova.lexico.setup.domain

import com.oriabova.lexico.setup.domain.model.SetupDetails
import kotlinx.coroutines.flow.Flow

interface ObserveSetupDetailsUseCase {
    operator fun invoke(): Flow<SetupDetails>
}