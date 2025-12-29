package com.oriabova.lexico.setup.domain

import com.oriabova.lexico.setup.domain.model.SetupDetails

interface GetSetupDetailsUseCase {
    suspend operator fun invoke(): SetupDetails
}