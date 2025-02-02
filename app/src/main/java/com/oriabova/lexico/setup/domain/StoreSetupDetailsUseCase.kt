package com.oriabova.lexico.setup.domain

import com.oriabova.lexico.setup.data.SetupDetails

interface StoreSetupDetailsUseCase {
    suspend operator fun invoke(setupDetails: SetupDetails)
}